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
        .then((response) => {
            if (response.status == 200) {
                alert('Supplier added; Redirecting to all suppliers page');
                window.location.href = currentLocation.includes('add-new-supplier') ? currentLocation.replace('add-new-supplier', 'view-suppliers') : currentLocationÒ;   // redirection.
            }
        }).catch((err) => {
        console.log(err);
    });
}


/**
 * Retrieve available suppliers from the database.
 * API call: http:<base_url>/supplier/
 * Response:
 *  [
 *      { supplier object },
 *      { supplier object },
 *      { supplier object }
 *  ]
 */
function getSuppliers() {
    axios.get(BASE_URL_LOCAL+'/supplier/')
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
    console.log(suppliers);
    // table header.
    let html =
        '<table class="table" id="'+ tagId +'">' +
            '<thead>' +
                '<tr>' +
                    '<th scope="col">Supplier Id</th>' +
                    '<th scope="col">Supplier Name</th>' +
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
            '<tr>'+
                '<td>' + supplier.supplierId + '</td>' +
                '<td>' + supplier.supplierName + '</td>' +
                '<td>' + supplier.bankAccountNo + '</td>' +
                '<td>' + supplier.address + '</td>' +
                '<td>' + supplier.email + '</td>' +
                '<td>' + supplier.phone + '</td>' +
                '<td>' + (supplier.isBanned ? 'Allowed' : 'Not allowed') + '</td>' +
                '<td>' +
                    '<div class="btn-group" role="group" aria-label="Basic example">' +
                        '<button type="button" class="btn btn-success"><i class="fas fa-edit"></i></button>' +
                        '<button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>' +
                    '</div>'
                '</td>' +
            '</tr>';
    })

    html += '</tbody></table>'; // closing tags.

    return html;

}

