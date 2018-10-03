

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


$('#btn-add-item').on('click', function (e) {
	e.preventDefault();
	addItemTo();
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
/*

function addRequest(){
	let data = {
			requestId : $('#orderId').val(),
			requestedPerson : $('#constructorname').val(),
			requestedDate : $('#requesteddate').val(),
			items : $('#menu').val(),
			quantity : $('#qty').val(),
			requestingDate : $('#requestingdate').val()
	}
	axios.post(BASE_URL_LOCAL + '/requestmaterial/add-new-request', data)
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
*/



/*
function addRequest() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var obj = {};
	for (var i of storedItems) {
		obj[i.itemName] = i.itemQty;
	}

	let data = {
			requestId: $('#orderId').val(),
			requestedPerson: $('#constructorname').val(),
			requestedDate: $('#requesteddate').val(),
		    items: obj,
		    requestingDate: $('#requestingdate').val(),
		
	}

	axios.post(BASE_URL_LOCAL + '/requestmaterial/add-new-request', data)
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

*/






function addRequest() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var obj = {};
	for (var i of storedItems) {
		obj[i.itemName] = i.itemQty;
	}

	let data = {
			requestId: $('#orderId').val(),
			requestedPerson: $('#constructorname').val(),
		    requestedDate: $('#requesteddate').val(),
            items: obj,
		    requestingDate: $('#requestingdate').val(),
		
	}

	axios.post(BASE_URL_LOCAL + '/requestmaterial/add-new-request', data)
		.then(function (response) {
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
            html +='<td align="right">'+request.requestId+'</td>' ;
            html +='<td align="right">' + request.requestedPerson +'</td>' ;
            html +='<td align="right">' + request.requestedDate + '</td>' ;
   
            html +='<td align="right">'  + getItemList(request.items) + '</td>' ;
            html +='<td align="right">' + request.quantity + '</td>' ;
            html +='<td align="right">' + request.requestingDate + '</td>' ;

        
            html +='</tr>';
           $('#view-requests tbody').append(html);
   	 });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}






function addItemTo() {
	if ($('#item-qty').val() != "") {

		let data = {
			itemId: $('#op-item-select').val(),
			itemName: $('#op-item-select :selected').text(),
			itemQty: $('#item-qty').val()
		}
		itemsArray.push(data);
		localStorage.setItem('items', JSON.stringify(itemsArray));

		loadItemTable();
	} else {
		$.notify("Quantity cannot be empty", "error");
	}
}

function loadItemTable() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	console.log(storedItems)

	for (var i of storedItems) {
		console.log(i.itemId + " | " + i.itemName);
		var html = '<tr>';
		html += '	<td class="nr-itemId"><center>' + i.itemId + '</center></td>';
		html += '	<td class="text-center">' + i.itemName + '</td>';
		html += '	<td class="text-center">' + i.itemQty + '</td>';
		html += '	<td class="text-center">' +
			'		<a href="#" title="" class="btn btn-danger btn-sm">\n' +
			'        	<span class="far fa-trash-alt" aria-hidden="true"></span>\n' +
			'        	<span><strong>Remove</strong></span></a>' +
			'		</a>' +
			'	</td>';
		html += '</tr>';
	}

	$('#request-item-table tbody').append(html);
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


function getItemList(items) {
	var html = '<ul>';
	for (var key in items) {
		if (items.hasOwnProperty(key)) {
			html += '<li>' + key + ' - ' + items[key] + '</li>';
		}
	}
	html += '</ul>';
	return html;
}

