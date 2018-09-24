/**
 * This JS file includes all Procurement Staff related Scripts
 * @author Tharindu TCJ
 */


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

$('#btn-add-site').on('click', function (e) {
    e.preventDefault();
    addNewSite();
});

$('#btn-add-item-to-site').on('click', function (e) {
    e.preventDefault();
    addItemToSite();
});

$('#btn-add-item').on('click', function (e) {
    e.preventDefault();
    addNewItem();
});






/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('manage-material-requests')) {
    console.log("You are on Manage Material Requests page");
    loadRequestedMaterialTable();
}

if (CURRENT_URL.includes('manage-black-list')) {
    console.log("You are on Manage Blacklist page");
}

if (CURRENT_URL.includes('manage-sites')) {
    console.log("You are on Manage Sites page");
    loadAllSites();
}

if (CURRENT_URL.includes('view-ratings')) {
    console.log("You are on View Ratings page");
}

if (CURRENT_URL.includes('add-new-site')) {
	console.log("You are on add new site page");
	generateItemSelectDropdown()
}

if (CURRENT_URL.includes('add-new-item')) {
    console.log("You are on add new item page");
    generateCategoryDropdown();
}


function loadRequestedMaterialTable(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/')
     .then(function (response) {
    	 console.log(response)
    	 response.data.forEach(item => {
    		 
    		 var html = '<tr>';
    		 html += '<td>'+item.orderId+'</td>';
    		 html += '<td class="nr-fid" scope="row">' + item.requestedPerson + '</td>';
    		 html += '<td >' + item.siteId + '</td>';
    		 html += '<td >' + getItemList(item.items) + '</td>';
    		 html += '<td>' + item.requestedDate + '</td>';
    		 html += '<td class="text-center">' + getImmediateButton(item.isImmediated) + '</td>';
    		 html += '<td><center>' +getApprovedButton(item.isProcumentApproved) +'</td>';
    		 html += '<td><center>' +
		             '<a href="#" title="" class="btn btn-danger btn-sm">\n' +
		             '        <span class="far fa-trash-alt" aria-hidden="true"></span>\n' +
		             '        <span><strong>Remove</strong></span></a>'+
		             '</a></center>'+
		             '</td>';
    		 html += '</tr>';
    		 
    		 $('#manage-material-requests tbody').append(html);
    	 });
     })
     .catch(function (error) {
         // handle error
         console.log(error);
     });
}

function addNewSite(){
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var obj = {};	
	for(var i of storedItems){obj[i.itemName] = i.itemQty;}	
	
	let data = {
		siteId : $('#site-id').val(),
		siteName : $('#site-name').val(),
		address : $('#address').val(),
		items: obj,
		storageCapacity : $('#storage-capacity').val(),
		currentCapacity : $('#current-capacity').val()
	}
	
	axios.post(BASE_URL_LOCAL + '/site/add-new-site', data)
    .then(function (response) {
    	alert(response.data)
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}



/**
 * This function add new item to inventory
 * @returns
 */
function addNewItem(){
	let data = {
		itemId : $('#item-id').val(),
		itemName : $('#item-name').val(),
		categoryId : $('#item-category-id').val(),
		price : $('#item-price').val(),
		deliveryInformation : $('#delivery-information').val(),
		isRestrictedItem : $('#restricted-item').val()
	}
	axios.post(BASE_URL_LOCAL + '/item/add-new-item', data)
    .then(function (response) {
    	alert(response.data)
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}


function addItemToSite(){
	if($('#item-qty').val() != ""){
		
		let data = {
			itemId : $('#op-item-select').val(),
			itemName : $('#op-item-select :selected').text(),
			itemQty : $('#item-qty').val()
		}
	
		itemsArray.push(data);
		localStorage.setItem('items', JSON.stringify(itemsArray));
		
		let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
		
		for(var i of storedItems){
			var html = '<tr>';
			html += '	<td>'+i.itemId+'</td>';
			html += '	<td class="nr-fid" scope="row">' + i.itemName + '</td>';
			html += '	<td >' + i.itemQty + '</td>';
			html += '</tr>';				
		}
						
		$('#item-list tbody').append(html);
	}else{
		alert("Quantity cannot be empty")
	}
}


function loadAllSites(){
	axios.get(BASE_URL_LOCAL + '/site/')
    .then(function (response) {
   	 console.log(response.data[0].siteManager);
   	 response.data.forEach(item => {
         let tr =
             '<tr>' +
                 '<td>'+item.siteId+'</td>' +
                 '<td class="nr-fid" scope="row">' + item.siteName + '</td>' +
                 '<td >' + item.address + '</td>' +
                 '<td >' + getItemList(item.items) + '</td>' +
                 '<td>' + item.storageCapacity + '</td>' +
                 '<td>' + item.currentCapacity + '</td>' +
                 '<td>' + item.siteManager.employeeName + '</td>' +
                 '<td class="text-center">' +
                     '<a href="#" title="" class="btn btn-primary btn-sm">' +
                     '        <span class="fas fa-edit" aria-hidden="true"></span> Edit' +
                     '</a>' +
                     '<a href="#" title="" class="btn btn-danger btn-sm">' +
                     '        <span class="far fa-trash-alt" aria-hidden="true"></span> Remove' +
                     '</a>' +
                 '</td>' +
             '</tr>';

        $('#manage-sites tbody').append(tr);
   	 });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

/* * * * *     Reusable functions     * * * * */

/**
 * Here we format date into below format
 * 			yyyy-mm-dd --> 2018-09-16
 * @author Tharindu TCJ
 */
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

/**
 * This function will generate the item object into list manner
 * @author Tharindu TCJ
 */
function getItemList(items){
	var html = '<ul>';
	for (var key in items) {
		if (items.hasOwnProperty(key)) {
			html += '<li>'+ key +' - '+items[key]+'</li>';
		}
	}
	html +=  '</ul>';
	return html;
}

function getApprovedButton(status){
	var btnClass = (status == 1) ? "btn btn-success btn-sm" : "btn btn-primary btn-sm" ;
	var btnText = (status == 1) ? "Approved" : "Approve" ;
	var isDisabled = (status == 1) ? "disabled" : "" ;
	
	
	var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fa fa-check" aria-hidden="true"></span>' +
		    '        <span><strong>'+btnText+'</strong></span></a>'+
		    '</button>';
	return html;
}

function getImmediateButton(status){
	var badgeClass = (status == 1) ? "badge badge-pill badge-danger" : "badge badge-pill badge-warning" ;
	var badgeText = (status == 1) ? "Yes" : "No" ;
	
	var html = '<h4><span class="'+badgeClass+'">'+badgeText+'</span></h4>';
	return html;
}

function generateCategoryDropdown(){
	axios.get(BASE_URL_LOCAL + '/category/')
    .then(function (response) {
    	console.log(response.data)
    	var html = '<select class="form-control" id="item-category-id">';
    	
    	response.data.forEach(item => {
    		html += '<option value="'+item.categoryId+'">'+item.categoryName+'</option>';
    	});
    	html += '</select>';
    	console.log(html)
    	$('#cat-select').append(html);
    }).catch(function (error) {
        // handle error
        console.log(error);
    });
}

function generateItemSelectDropdown(){
	axios.get(BASE_URL_LOCAL + '/item/')
    .then(function (response) {
    	console.log(response.data)
    	var html = '<select class="form-control" id="op-item-select">';
    	
    	response.data.forEach(item => {
    		html += '<option value="'+item.itemId+'">'+item.itemName+'</option>';
    	});
    	html += '</select>';
    	console.log(html)
    	$('#item-select').append(html);
    }).catch(function (error) {
        // handle error
        console.log(error);
    });
}
