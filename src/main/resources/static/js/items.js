/**
 * @author Tharushi
 *
 * Responsible for handling the UI in adding/editing/removing items by each supplier.
 */
const BASE_URL_LOCAL = 'http://localhost:9000';
const BASE_URL_PROD = '';

// instead of directly using one of the above, assign the appropriate one to BASE URL as follows.
let BASE_URL = BASE_URL_LOCAL;


// current location of the page that is calling this script.
let currentLocation = window.location.href;

/* * * * Event Triggers * * * */
// to add an item.
$('#btn-add-item').on('click', function () {
    addItem(false);
});
// to update an item.
$('#btn-edit-item').on('click', function () {
    addItem(true);
});


/* * * * Functions * * * */

// to add or update an item.
function addItem(update) {
    // get data from html form.
    let data = {
        itemId: $('#item-id').val(),
        itemName: $('#item-description').val(),
        quantity: $('#available-quantity-in-store').val(),
        price: $('#unit-price').val(),
        supplierId: 'SP10980'   // this should be taken from localStorage.
    };

    if (update) {
        axios.put(BASE_URL + '/item', data)
            .then((response) => {
                if (response.status == 200) {
                    alert('Item Updated; Redirecting to all items page');
                    window.location.href = currentLocation.includes('edit-supply-item') ? currentLocation.replace('edit-supply-item', 'view-supply-items') : currentLocation;   // redirection.
                }
            }).catch((err) => {
            console.log(err);
        });
    }
    else {
        axios.post(BASE_URL + '/item/add-new-item', data)
            .then((response) => {
                if (response.status == 200) {
                    alert('Item added; Redirecting to all items page');
                    window.location.href = currentLocation.includes('add-supply-item') ? currentLocation.replace('add-supply-item', 'view-supply-items') : currentLocation;   // redirection.
                }
            }).catch((err) => {
            console.log(err);
        });
    }
}

/**
 * Delete an item; get user confirmation first.
 *
 */
function deleteItem(button) {
    // get the id of the button which is relevant to the item that we want to delete,
    // since we embed the item id in the button id.
    let itemId = button.id.replace('delete-item-', '');

    // ask for user confirmation.
    if (confirm('Are you sure you want to delete the item(' + itemId + ')')) {
        // yes/true
        // use an else clause if there should be an action for no/false.
        axios.delete(BASE_URL + '/item/' + itemId)
            .then(response => {
                if (response.status == 200) {
                    alert('Item(' + itemId + ') deleted successfully.');

                    // refresh the page to reflect the change.
                    location.reload();
                }
            })
    }
}


        /**
 * Retrieve available items provided by this supplier from the database.
 * API call: http://<base_url>/item/<supplierId>/items
 *
 * IMPORTANT: We only get items of the supplier who's currently accessing the page.
 *
 * Response:
 *  [
 *      { item object },
 *      { item object },
 *      { item object }
 *  ]
 */
function getItems() {
    let supplierId = 'SP10980';

    axios.get(BASE_URL_LOCAL+'/item/' + supplierId + '/items')
        .then(response => {
            if (response.status == 200) {
                $('#item-table-container').append(renderItemTable('item-table', response.data));

                // apply data-tables transformation; note that since we dynamically insert this,
                // table, we need to bind it before calling datatables on the table.
                window.$('#item-table').DataTable();
            }
        })
        .catch(err => {
            console.log(err);
        });
}

/**
 * view all items in a searchable table.(using data-tables library for interactivity)
 *
 * @param items: list of items(i.e: response from the backend). this has the following structure:-
 *                      [
 *                          { item object },
 *                          { item object},
 *                          ....
 *                      ]
 *
 */
function renderItemTable(tagId, items) {
    // render the table as a bootstrap table and use data tables to,
    // make it interactive.

    // table header.
    let html =
        '<table class="table" id="'+ tagId +'">' +
        '<thead>' +
        '<tr>' +
        '<th scope="col">Item Id</th>' +
        '<th scope="col">Item Name</th>' +
        '<th scope="col">Quantity in Store</th>' +
        '<th scope="col">Unit Price</th>' +
        '<th scope="col">Operations</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';  // be sure to end this tag in the end.

    // add the rows(a row per supplier).
    items.forEach(item => {
        html +=
            '<tr>'+
            '<td>' + item.itemId + '</td>' +
            '<td>' + item.itemName + '</td>' +
            '<td>' + item.quantity + '</td>' +
            '<td>' + item.price + '</td>' +
            '<td>' +
            '<div class="btn-group" role="group" aria-label="Basic example">' +
            '<button id="edit-item-' + item.itemId + '" type="button" class="btn btn-outline-success" onclick="goToEditPage(this);"><i class="fas fa-edit"></i></button>' +
            '<button id="delete-item-' + item.itemId + '" type="button" class="btn btn-outline-danger" onclick="deleteItem(this);"><i class="fas fa-trash-alt"></i></button>' +
            '</div>' +
            '</td>' +
            '</tr>';
    })

    html += '</tbody></table>'; // closing tags.

    return html;
}


/**
 * This is responsible for redirecting to the edit page for the relevant item.
 *
 * @param itemId: item id of the item that we want to edit the information of.
 */
function goToEditPage(button) {
    // get the relevant item's id from the button.
    let id = button.id;     // id is in this format: edit-item-IT123 where IT123 is the itemId.
    let itemId = id.includes('edit-item-') ? id.replace('edit-item-', '') : '';

    // navigate to edit page.
    // we embed the item id in the edit page's URL so that the script/function running on that page,
    // can identify which item needs to be edited.
    // url: <host>:<port>/suppliers/items/edit-supply-item.html#<item id>.
    // having something starting with a hash in the end of the url won't do any harm to url navigation.
    window.location.href = currentLocation.includes('view-supply-items') ? currentLocation.replace('view-supply-items', 'edit-supply-item') + '#' + itemId : currentLocation;   // redirection.
}


/**
 * This function will fill the fields in the edit form with the details relevant to the item,
 * that the user is editing.
 */
function loadItemDetailsForEditing() {
    if (currentLocation.includes('#')) {
        let itemId = currentLocation.split('#').pop();  // when u split by # you get the full url and the item id at the end. we only need the latter.

        // get the details of the above item from the database and show it in the form.
        axios.get(BASE_URL_LOCAL + '/item/' + itemId)
            .then(response => {
                if (response.status == 200) {
                    let item = response.data;

                    // now that we have details of the item, we can populate the form with existing info.
                    if (item) {
                        $('#item-id').val(item.itemId);
                        $('#item-description').val(item.itemName);
                        $('#available-quantity-in-store').val(item.quantity);
                        $('#unit-price').val(item.price);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            })
    }
}

