const BASE_URL_LOCAL = 'http://localhost:9000';
const BASE_URL_PROD = '';

// instead of directly using one of the above, assign the appropriate one to BASE URL as follows.
let BASE_URL = BASE_URL_LOCAL;


// current location of the page that is calling this script.
let currentLocation = window.location.href;


function addSiteManager() {
    let data = {
        employeeId: $('#employee-id').val(),
        employeeName: $('#name').val(),
        employeeType: 'Manager',
        address: $('#address').val(),
        email: $('#email').val(),
        phone: $('#phone').val()
    };

    axios.post(BASE_URL + '/employee/site-manager', data)
        .then((response) => {
            if (response.status == 200) {
                alert('Site manager added; Redirecting to home.');
                window.location.href = currentLocation.includes('add-new-sitemanager') ? currentLocation.replace('add-new-sitemanager', 'sitemanager-dashboard') : currentLocation;   // redirection.
            }
        })
        .catch(err => {console.log(err)});

}
