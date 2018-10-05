/**
 * @author Anushka
 *
 * Responsible for handling the UI in adding/editing/removing policies.
 */
const BASE_URL_LOCAL = 'http://localhost:9000';
const BASE_URL_PROD = '';

// instead of directly using one of the above, assign the appropriate one to BASE URL as follows.
let BASE_URL = BASE_URL_LOCAL;


// current location of the page that is calling this script.
let currentLocation = window.location.href;

/* * * * Event Triggers * * * */
// to add an item.
$('#btn-add-policy').on('click', function () {
    addPolicy();
});


/* * * * Functions * * * */
function addPolicy() {
    let data = {
        policyId: $('#policy-id').val(),
        description: $('#policy-description').val()
    };

    axios.post(BASE_URL + '/policy', data)
        .then(response => {
            if (response.status == 200) {
                // redirect since the request is successful.
                alert('Policy added; Redirecting to all policies page');
                window.location.href = currentLocation.includes('add-policy') ? currentLocation.replace('add-policy', 'view-policies') : currentLocation;   // redirection.
            }
        })
}

/**
 * Retrieve available policies from the database.
 * API call: http://<base_url>/policy/all
 *
 * Response:
 *  [
 *      { policy object },
 *      { policy object },
 *      { policy object }
 *  ]
 */
function getPolicies() {

    axios.get(BASE_URL_LOCAL+'/policy/all')
        .then(response => {
            if (response.status == 200) {
                $('#policy-table-container').append(renderItemTable('policy-table', response.data));

                // apply data-tables transformation; note that since we dynamically insert this,
                // table, we need to bind it before calling datatables on the table.
                window.$('#policy-table').DataTable();
            }
        })
        .catch(err => {
            console.log(err);
        });
}

/**
 * view all items in a searchable table.(using data-tables library for interactivity)
 *
 * @param policies: list of policies(i.e: response from the backend). this has the following structure:-
 *                      [
 *                          { policy object },
 *                          { policy object},
 *                          ....
 *                      ]
 *
 */
function renderItemTable(tagId, policies) {
    // render the table as a bootstrap table and use data tables to,
    // make it interactive.

    // table header.
    let html =
        '<table class="table" id="'+ tagId +'">' +
        '<thead>' +
        '<tr>' +
        '<th scope="col">Policy Id</th>' +
        '<th scope="col">Description</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>';  // be sure to end this tag in the end.

    // add the rows(a row per supplier).
    policies.forEach(policy => {
        html +=
            '<tr>'+
            '<td>' + policy.policyId + '</td>' +
            '<td>' + policy.description + '</td>' +
            '</tr>';
    })

    html += '</tbody></table>'; // closing tags.

    return html;
}

