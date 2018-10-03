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
        price: $('#unit-price').val()
    };

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

// retrieve all items for this supplier from database.
function getItems() {
    
}