

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
	addRequiredItem();
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


if (CURRENT_URL.includes('search-requests')) {
    console.log("You are on search requests page")
    searchRequests();
}

if (CURRENT_URL.includes('request-order')) {
	generateItemSelectDropdown();
}


function searchRequests() {
    axios.get(BASE_URL_LOCAL + '/requestmaterial/')
    .then(response => {
        if (response.status == 200) {
            console.log(response.data);
            $('#view-search-req').append(searchRequestTable('request-table', response.data));
            window.$('#request-table').DataTable();
        }
    })
    .catch(err => {
        console.log(err);
    });
}


function searchRequestTable(tableId, req) {
        let html =
            '<table class="table table-striped table-bordered" id="'+ tableId +'">' +
            '<thead>' +
            '<tr>' +
            '<th align="center"scope="col">Request ID</th>' +
            '<th align="center" scope="col">Requested Person</th>' +
            '<th align="center" scope="col">Requested Date</th>' +
            '<th align="center" scope="col">Items </th>' +
            '<th align="center" scope="col">Requesting Date</th>' +
            '</tr>' +
            '</thead>' +
            '<tbody>';  
    
        
        req.forEach(request => {
            html +=
                '<tr>'+
               '<td align="right">'+request.requestId+'</td>' +
               '<td align="right">' + request.requestedPerson +'</td>' +
                '<td align="right">' + request.requestedDate + '</td>' +  
                '<td align="right">'  + getItemList(request.items) + '</td>' +     
                '<td align="right">' + request.requestingDate + '</td>' +
                         
               '</tr>';
                   
        });
    
        html += '</tbody></table>'; 
    
        return html;
    }


//generate item selection dropdown list in add request page
function generateItemSelectDropdown() {
	axios.get(BASE_URL_LOCAL + '/item/')
		.then(function (response) {
			console.log(response.data)
			var html = '<select class="form-control" id="op-item-select">';

			Object.keys(response.data).forEach(supplierAsKey => {
				response.data[supplierAsKey].forEach(item => {
					html += '<option value="' + item.itemId + '">' + item.itemName + '</option>';
				});
			});


			html += '</select>';
			$('#item-select').append(html);
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
}




//constructor add requests-add request page
function addRequest() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var obj = {};
	for (var i of storedItems) {
		obj[i.itemName] = i.itemQty;
	}

	let data = {
			requestId: $('#requestId').val(),
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


//load all the requests by constructor in view request page
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
            html +='<td align="right">' + request.requestingDate + '</td>' ;   
            html +='</tr>';
           $('#view-requests tbody').append(html);
   	 });
    })
    .catch(function (error) {
        console.log(error);
    });
}


function addRequiredItem() {
	if ($('#item-qty').val() != "") {

		let data = {
			itemId: $('#op-item-select').val(),
			itemName: $('#op-item-select :selected').text(),
			itemQty: $('#item-qty').val()
		}		
		itemsArray.push(data);
		localStorage.setItem('items', JSON.stringify(itemsArray));
		loadAddedItemTable();
	} else {
		$.notify("Quantity cannot be empty", "error");
	}

}


function loadAddedItemTable() {
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


//reset the add request form
function resetAddRequestForm(){
    if (CURRENT_URL.includes('request-order')){    
        
           $('#requestId').val(''),
           $('#constructorname').val(''),
           $('#requesteddate').val(''),
           $('#item-select').val(''),
           $('#item-qty').val('')
           $('#requestingdate').val('')
           
   }
}




/**
 * Click event in site-item-table
 */
$(document).on('click', '#request-item-table .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var itemId = $(this).closest("tr").find(".nr-itemId").text();
	console.log(itemId);
	var r = confirm("Are you sure you want to remove this item ? This action cannot be undone");
	if (r == true) {


		let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];

		var index = storedItems.findIndex(function (item, i) {
			return item.itemId === itemId
		});

		console.log("Index : " + index);
		if (index != -1) {
			storedItems.splice(index, 1);
		}

		localStorage.setItem('items', JSON.stringify(storedItems));
		$("#item-list tbody").empty();

		loadItemTable();


		$.notify(itemId, "success");
	}
});












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


