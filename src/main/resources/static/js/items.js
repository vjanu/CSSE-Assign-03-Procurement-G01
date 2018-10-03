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
                $('#item-table-container').append(renderSupplierTable('item-table', response.data));

                // apply data-tables transformation; note that since we dynamically insert this,
                // table, we need to bind it before calling datatables on the table.
                window.$('#item-table').DataTable();
            }
        })
        .catch(err => {
            console.log(err);
        });
}