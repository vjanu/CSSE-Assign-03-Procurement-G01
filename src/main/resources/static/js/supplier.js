/**
 * @author Tharushi
 *
 * Responsible for handling the UI in adding/editing/removing suppliers.
 */
const BASE_URL_LOCAL = 'http://localhost:9000';
const BASE_URL_PROD = '';

// instead of directly using one of the above, assign the appropriate one to BASE URL as follows.
let BASE_URL = BASE_URL_LOCAL;


// current location of the page that is calling this script.
let currentLocation = window.location.href;

/* * * * Event Triggers * * * */
// to add a supplier.
$('#btn-add-supplier').on('click', function () {
    addSupplier(false);
});
// to update a supplier.
$('#btn-edit-supplier').on('click', function () {
    addSupplier(true);
});

/* * * * Functions * * * */

/**
 * Add or update a supplier to/in database.
 *
 * @param update indicates if this an update query or a totally new insertion.
 */
function addSupplier(update) {
    // get data from html form.
    let data = {
        supplierId: $('#supplier-id').val(),
        supplierName: $('#supplier-name').val(),
        bankName : $('#bank-name').val(),
        bankAccountNo: $('#bank-account-number').val(),
        address: $('#address').val(),
        email: $('#email').val(),
        phone: $('#phone').val(),
        isBanned: $('#availability').val()
    };

    if (update) {
        axios.put(BASE_URL + '/supplier', data)
            .then((response) => {
                if (response.status == 200) {
                    alert('Supplier updated; Redirecting to all suppliers page');
                    window.location.href = currentLocation.includes('edit-supplier') ? currentLocation.replace('edit-supplier', 'view-suppliers') : currentLocation;   // redirection.
                }
            }).catch((err) => {
            console.log(err);
        });
    }
    else {
        axios.post(BASE_URL + '/supplier', data)
            .then((response) => {
                if (response.status == 200) {
                    alert('Supplier added; Redirecting to all suppliers page');
                    window.location.href = currentLocation.includes('add-new-supplier') ? currentLocation.replace('add-new-supplier', 'view-suppliers') : currentLocation;   // redirection.
                }
            }).catch((err) => {
            console.log(err);
        });
    }
}

/**
 * Delete a supplier;  get user confirmation first.
 *
 */
function deleteSupplier(button) {
    // get the id of the button which is relevant to the supplier that we want to delete,
    // since we embed the supplier id in the button id.
    let supplierId = button.id.replace('delete-supplier-', '');

    // ask for user confirmation.
    if (confirm('Are you sure you want to delete the supplier(' + supplierId  + ')')) {
        // yes/true
        // use an else clause if there should be an action for no/false.
        axios.delete(BASE_URL + '/supplier/' + supplierId)
            .then(response => {
                if (response.status == 200) {
                    alert('Supplier(' + supplierId + ') deleted successfully.');

                    // refresh the page to reflect the change.
                    location.reload();
                }
            })
    }
}



/**
 * Retrieve available suppliers from the database.
 * API call: http://<base_url>/supplier/
 * Response:
 *  [
 *      { supplier object },
 *      { supplier object },
 *      { supplier object }
 *  ]
 */
function getSuppliers() {
    axios.get(BASE_URL_LOCAL + '/supplier/')
        .then(response => {
            if (response.status == 200) {
                $('#supplier-table-container').append(renderSupplierTable('supplier-table', response.data));

                // apply data-tables transformation; note that since we dynamically insert this,
                // table, we need to bind it before calling datatables on the table.
                window.$('#supplier-table').DataTable();
            }
        })
        .catch(err => {
            console.log(err);
        });
}

/**
 * Retrieve all orders for a specific supplier.
 * API call: http://<base_url>/order?supplier=<supplier_id>
 * Response:
 *  {
 *      <supplier id>: [
 *          { purchase order object },
 *          { purchase order object }
 *      ]
 *  }
 */
function getPurchaseOrders() {
    let supplierId = 'SP10980';
    axios.get(BASE_URL_LOCAL + '/order?supplier=' + supplierId + '')
        .then(response => {
            if (response.status == 200) {
                console.log(response.data);
                $('#order-table-container').append(renderPurchaseOrderTable('order-table', response.data[supplierId]));

                // apply data-tables transformation; note that since we dynamically insert this,
                // table, we need to bind it before calling datatables on the table.
                window.$('#order-table').DataTable();
            }
        })
        .catch(err => {
            console.log(err);
        });
}

/**
 * view all suppliers in a searchable table.(using data-tables library for interactivity)
 *
 * @param suppliers: list of suppliers(i.e: response from the backend). this has the following structure:-
 *                      [
 *                          { supplier object },
 *                          { supplier object},
 *                          ....
 *                      ]
 *
 */
function renderSupplierTable(tagId, suppliers) {
    // render the table as a bootstrap table and use data tables to,
    // make it interactive.

    // table header.
    let html =
        '<table class="table" id="' + tagId + '">' +
        '<thead>' +
        '<tr>' +
        '<th scope="col">Supplier Id</th>' +
        '<th scope="col">Supplier Name</th>' +
        '<th scope="col">Bank Name</th>' +
        '<th scope="col">Bank Account Number</th>' +
        '<th scope="col">Address</th>' +
        '<th scope="col">Email</th>' +
        '<th scope="col">Phone</th>' +
        '<th scope="col">Banned?</th>' +
        '<th scope="col">Modify</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';  // be sure to end this tag in the end.

    // add the rows(a row per supplier).
    suppliers.forEach(supplier => {
        html +=
            '<tr>' +
            '<td>' + supplier.supplierId + '</td>' +
            '<td>' + supplier.supplierName + '</td>' +
            '<td>' + supplier.bankName + '</td>' +
            '<td>' + supplier.bankAccountNo + '</td>' +
            '<td>' + supplier.address + '</td>' +
            '<td>' + supplier.email + '</td>' +
            '<td>' + supplier.phone + '</td>' +
            '<td>' + (supplier.isBanned ? 'Allowed' : 'Not allowed') + '</td>' +
            '<td>' +
            '<div class="btn-group" role="group" aria-label="Basic example">' +
            '<button id="edit-supplier-' + supplier.supplierId + '" type="button" class="btn btn-outline-success" onclick="goToEditPage(this);"><i class="fas fa-edit"></i></button>' +
            '<button id="delete-supplier-' + supplier.supplierId + '" type="button" class="btn btn-outline-danger" onclick="deleteSupplier(this);"><i class="fas fa-trash-alt"></i></button>' +
            '</div>'
        '</td>' +
        '</tr>';
    })

    html += '</tbody></table>'; // closing tags.

    return html;
}


/**
 *
 * @param tagId: id of the table.
 * @param purchaseOrders: list of purcahse orders for a given supplier. the response is in following,
 *                          format:-
 *                              {
 *                                  "supplier id": [
 *                                      { purchase order object },
 *                                      { purchase order object },
 *                                      { purchase order object }
 *                                  ]
 *                              }
 */
function renderPurchaseOrderTable(tagId, purchaseOrders) {
// render the table as a bootstrap table and use data tables to,
    // make it interactive.

    // table header.
    let html =
        '<table class="table" id="' + tagId + '">' +
        '<thead>' +
        '<tr>' +
        '<th scope="col">Order Id</th>' +
        '<th scope="col">Items</th>' +
        '<th scope="col">Delivery Address</th>' +
        '<th scope="col">Order Date</th>' +
        '<th scope="col">Order Status</th>' +
        '<th scope="col">Operations</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';  // be sure to end this tag in the end.

    // add the rows(a row per supplier).
    purchaseOrders.forEach(order => {
        html +=
            '<tr>' +
            '<td>' + order.orderId + '</td>' +
            '<td>' + getItemsFromList(order.items) + '</td>' +
            '<td>' + order.deliverySite.address + '</td>' +
            '<td>' + order.orderDate + '</td>' +
            '<td>' + order.orderStatus + '</td>' +
            '<td>' + 'operations' + '</td>' +
            '</tr>';
    });

    html += '</tbody></table>'; // closing tags.

    return html;
}


/**
 * this will take an arry that has item objects and will put,
 * item names into one string to be shown in a table or something.
 *
 * @param itemsList: an array where each element is a item object.(refer item.java in model package).
 */
function getItemsFromList(itemsList) {
    let str = '';

    itemsList.forEach(item => {
        str += item.itemName + ',\n';
    });

    // remove the last comma which is not needed.

    return str;
}

/**
 * This is responsible for redirecting to the edit page for the relevant supplier.
 *
 * @param button: DOM Element of the button that was clicked.
 */
function goToEditPage(button) {
    // get the relevant item's id from the button.
    let id = button.id;     // id is in this format: edit-item-IT123 where IT123 is the itemId.
    let supplierId = id.includes('edit-supplier-') ? id.replace('edit-supplier-', '') : '';

    // navigate to edit page.
    // we embed the item id in the edit page's URL so that the script/function running on that page,
    // can identify which item needs to be edited.
    // url: <host>:<port>/suppliers/items/edit-supply-item.html#<item id>.
    // having something starting with a hash in the end of the url won't do any harm to url navigation.
    window.location.href = currentLocation.includes('view-suppliers') ? currentLocation.replace('view-suppliers', 'edit-supplier') + '#' + supplierId : currentLocation;   // redirection.
}

/**
 * This function will fill the fields in the edit form with the details relevant to the supplier,
 * that the user is editing.
 */
function loadSupplierDetailsForEditing() {
    if (currentLocation.includes('#')) {
        let itemId = currentLocation.split('#').pop();  // when u split by # you get the full url and the item id at the end. we only need the latter.

        // get the details of the above item from the database and show it in the form.
        axios.get(BASE_URL_LOCAL + '/supplier/' + itemId)
            .then(response => {
                if (response.status == 200) {
                    let supplier = response.data;

                    // now that we have details of the item, we can populate the form with existing info.
                    if (supplier) {
                        $('#supplier-id').val(supplier.supplierId);
                        $('#supplier-name').val(supplier.supplierName);
                        $('#bank-name').val(supplier.bankName);
                        $('#bank-account-number').val(supplier.bankAccountNo);
                        $('#address').val(supplier.address);
                        $('#email').val(supplier.email);
                        $('#phone').val(supplier.phone);
                        $('#availability').val(supplier.isBanned);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            })
    }
}