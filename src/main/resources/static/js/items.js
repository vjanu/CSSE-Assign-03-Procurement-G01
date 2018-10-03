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

}