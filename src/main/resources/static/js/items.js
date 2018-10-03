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
// to add a supplier.
$('#btn-add-item').on('click', function () {
    addItem();
});


/* * * * Functions * * * */

// to add an item.
function addItem() {
    // get data from html form.
    let data = {
        itemId: $('#item-id').val(),
        itemName: $('#item-description').val(),
        quantity: $('#available-quantity-in-store').val(),
        price: $('#unit-price').val(),
        supplierId: 'SP10980'
    };

    console.log(data);
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
            '</tr>';
    })

    html += '</tbody></table>'; // closing tags.

    return html;
}
