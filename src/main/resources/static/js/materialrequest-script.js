

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
 //  loadRequestMaterials();
}


if (CURRENT_URL.includes('request-order')) {
    console.log("You are on add request page");
   
}

if (CURRENT_URL.includes('view-requests')) {
    console.log("You are on view-requests page")
    loadConstructorRequests();
    
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
    	alert(response.data);
    	$.notify(response.data, "success");
    })
    .catch(function (error) {
        // handle error
        console.log(error);
        $.notify("Request not added", "error");;    
    });
}



function loadConstructorRequests(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/')
    .then(function (response) {
   	 console.log(response)
   	 response.data.forEach(request => {


            var html = '<tr>';
            html +='<td align="right">'+request.orderId+'</td>' ;
            html +='<td align="right">' + request.requestedPerson +'</td>' ;
            html +='<td align="right">' + request.requestedDate + '</td>' ;
            html +='<td align="right">' + request.item + '</td>' ;
            html +='<td align="right">' + request.quantity + '</td>' ;
            html +='<td align="right">' + request.requestingDate + '</td>' ;
//            html +='<td align="center">' + getItemList(request.items) + '</td>' ;
        
            html +='</tr>';
           $('#view-requests tbody').append(html);
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




