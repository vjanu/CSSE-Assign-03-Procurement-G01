let BASE_URL_LOCAL = 'http://localhost:9000';
let USER_INFO = 'user-info';
let CURRENT_URL = window.location.href;




/* * * * *     Event Triggers     * * * * */
$('#btn-logout').on('click', function (e) {
	e.preventDefault();
	localStorage.removeItem(USER_INFO);
	window.location.href = "index.html";
});


$('#btn-login').on('click', function (e) {
	 validateUserSignedIn();
});


/***
 * This function validate user
 */
function validateUserSignedIn() {
    let data = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value
    }

    axios.post(BASE_URL_LOCAL + '/login/validate-user', data)
        .then(response => {
            console.log(response.data);
            if (response.data.success) {
                let user_info = {
                    UserType: response.data.userType,
                    userId: response.data.userId
                }
                localStorage.setItem(USER_INFO, window.btoa(JSON.stringify(user_info)));
                // localStorage.setItem('user_info', (JSON.stringify(user_info)));
                if(user_info.UserType == 'Procurement-Staff'){
                    window.location.href = "manage-sites.html";
                }else if(user_info.UserType == 'Sitemanager'){
                    window.location.href = "sitemanager-dashboard.html";
                }else if(user_info.UserType == 'Constructor'){
                    window.location.href = "constructor-dashboard.html";
                }else if(user_info.UserType == 'Accounting Staff'){
                    window.location.href = "accountingstaff-dashboard.html";
                }
                
                else{
                    alert("Invalid login credentials")
                }
            } else {
                alert("Invalid login credentials");
            }
        })
        .catch(error => {
            alert("Invalid login credentials")
            console.log(error);
        })
}


