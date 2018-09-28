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
let currentLocation = window.href;

/* * * * Event Triggers * * * */
// to add a supplier.
$('#btn-add-supplier').on('click', function () {
    addSupplier();
});


/* * * * Functions * * * */
// to add a supplier
function addSupplier() {
    // get data from html form.
    let data = {
        supplierName: $('#supplier-name').val(),
        bankAccountNo: $('#bank-account-number').val(),
        address: $('#address').val(),
        email: $('#email').val(),
        phone: $('#phone').val(),
        isBanned: $('#availability').val()
    };

    axios.post(BASE_URL + '/supplier', data)
    .then((result) => {
        
    }).catch((err) => {
        
    });

    console.log(data);
}

