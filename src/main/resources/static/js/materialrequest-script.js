

/* * * * *     Global Variables     * * * * */
let BASE_URL_LOCAL = 'http://localhost:9000';
let USER_INFO = 'user-info';
let CURRENT_URL = window.location.href;

let itemsArray = [];


/* * * * *     Event Triggers     * * * * */
$('#btn-logout').on('click', function (e) {
    e.preventDefault();
    localStorage.removeItem(USER_INFO);
    window.location.href = "index.html";
});


$('#btn-add-request').on('click', function (e) {
    e.preventDefault();
    addRequest();
});






/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('request-order')) {
    console.log("You are on Request material page");
   loadRequestMaterials();
}


if (CURRENT_URL.includes('request-order')) {
    console.log("You are on add request page");
   
}




function addRequest(){
	let data = {
		 orderId : $('#orderId').val(),
		 requestedPerson : $('#constructorname').val(),
		 requestedDate : $('#requesteddate').val(),
		 items : $('#menu').val(),
		 quantity : $('#qty').val(),
		 requestingDate : $('#requestingdate').val()
	}
	axios.post(BASE_URL_LOCAL + '/requestmaterial/add-new-order', data)
    .then(function (response) {
    	alert(response.data)
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

function loadRequestMaterials(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/add-new-order')
    .then(function (response) {
   	 console.log(response)
   	 response.data.forEach(item => {
   		 
   		 var html = '<tr>';
   		 html += '<td>'+item.orderId+'</td>';
   		 html += '<td class="nr-fid" scope="row">' + item.requestedPerson + '</td>';
   		 html += '<td >' + item.requestedDate + '</td>';
   		 html += '<td >' + getItemList(item.items) + '</td>';
   		 html += '<td>' + item.quantity + '</td>';
   		 html += '<td >' + item.requestingDate + '</td>';
   		 html += '</tr>';
   		 
   		 $('#manage-site tbody').append(html);
   	 });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}









function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}




