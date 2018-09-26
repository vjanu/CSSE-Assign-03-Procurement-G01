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


$(document).on('click', '#manage-material-requests .btn-primary', function(e){ 
	e.preventDefault();
    e.stopPropagation();
    var oid = $(this).closest("tr").find(".nr-oid").text();
    console.log(oid);
    var r = confirm("Are you sure? Thi action cannot be undone");
    if (r == true) {
        approveRequestedMaterial(oid);
    }
});


$(document).on('click', '#manage-material-requests .btn-danger', function(e){ 
	e.preventDefault();
    e.stopPropagation();
    var oid = $(this).closest("tr").find(".nr-oid").text();
    console.log(oid);
    var r = confirm("Are you sure want to delete this request? Thi action cannot be undone");
    if (r == true) {
        removeMaterialRequest(oid);
    }
});






/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('manage-material-requests')) {
    console.log("You are on Manage Material Requests page");
    loadRequestedMaterialTable();
}

if (CURRENT_URL.includes('manage-black-list')) {
    console.log("You are on Manage Blacklist page");
    loadAllSuppliers()
}

if (CURRENT_URL.includes('manage-sites')) {
    console.log("You are on Manage Sites page");
    loadAllSites();
}

if (CURRENT_URL.includes('view-constructor-ratings')) {
    console.log("You are on View constructor Ratings page");
    loadConstructorRatings();
}

if (CURRENT_URL.includes('add-new-site')) {
	console.log("You are on add new site page");
	generateItemSelectDropdown();
    generateSiteManagersDropdown();
}

if (CURRENT_URL.includes('add-new-item')) {
    console.log("You are on add new item page");
    generateCategoryDropdown();
}


function removeMaterialRequest(rid){
	axios.delete(BASE_URL_LOCAL + '/requestmaterial/remove/'+rid)
    .then(function (response) {
   	 	console.log(response);
		loadRequestedMaterialTable();
   	}).catch(function (error) {
        // handle error
        console.log(error);
    });
}


function approveRequestedMaterial(oid){
	console.log(oid);
	let data = {
			"isProcumentApproved":"1"
	}
	axios.put(BASE_URL_LOCAL + '/requestmaterial/update/'+oid, data)
    .then(function (response) {
   	 	console.log(response);
   	 	$.notify("Successfully Approved", "success");
		loadRequestedMaterialTable();
   	}).catch(function (error) {
        // handle error
        console.log(error);
    });
}


function loadConstructorRatings(){
	axios.get(BASE_URL_LOCAL + '/ratings/')
    .then(function (response) {
   	 console.log(response)
   	 response.data.forEach(item => {
   		var html = '<tr>';
		 html += '<td>'+item.purchaseOrderReference+'</td>';
		 html += '<td class="nr-fid" scope="row">' + item.supplierName + '</td>';
		 html += '<td>' + item.constructorName + '</td>';
		 html += '<td class="text-center">' + item.deliveryEfficiency + '</td>';
		 html += '<td><center>' +item.supportiveness +'</td>';
		 html += '<td><center>' +item.workOnTime +'</td>';
		 html += '<td><center>' +item.overallRate +'</td>';
		 html += '<td><center>' +item.feedback +'</td>';
		 html += '<td><center>' +getBlacklistButton(false)+'</td>';
		 html += '</tr>';
		 
		 $('#view-constructor-ratings tbody').append(html);
   	 });
   	}).catch(function (error) {
        // handle error
        console.log(error);
    });
    
}

function loadAllSuppliers(){
	axios.get(BASE_URL_LOCAL + '/sup/')
    .then(function (response) {
   	 console.log(response)
   	 response.data.forEach(item => {
   		var html = '<tr>';
		 html += '<td>'+item.supId+'</td>';
		 html += '<td class="nr-fid" scope="row">' + item.supName + '</td>';
		 html += '<td >' + item.supName + '</td>';
		 html += '<td>Supplier</td>';
		 html += '<td>' + item.supName + '</td>';
		 html += '<td class="text-center">' + getBlacklistBadge(item.isBanned) + '</td>';
		 html += '<td><center>' +getBlacklistButton(item.isBanned) +'</td>';
		 html += '</tr>';
		 
		 $('#manage-black-list tbody').append(html);
   	 });
   	}).catch(function (error) {
        // handle error
        console.log(error);
    });
    
}


function loadRequestedMaterialTable(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/sitemanager-approved/')
     .then(function (response) {
    	 console.log(response);
    	 $("#manage-material-requests tbody").empty();
    	 response.data.forEach(item => {
    		 
    		 var html = '<tr>';
    		 html += '<td class="nr-oid" scope="row">'+item.orderId+'</td>';
    		 html += '<td class="text-center">' + item.requestedPerson + '</td>';
    		 html += '<td class="text-center">' + item.siteId + '</td>';
    		 html += '<td >' + getItemList(item.items) + '</td>';
    		 html += '<td class="text-center">' + item.requestedDate + '</td>';
    		 html += '<td class="text-center">' + getImmediateButton(item.isImmediated) + '</td>';
    		 html += '<td class="text-center">' +getApprovedButton(item.isProcumentApproved) +'</td>';
    		 html += '<td><center>' +
		             '<a href="#" title="" class="btn btn-danger btn-sm">\n' +
		             '        <span class="far fa-trash-alt" aria-hidden="true"></span>\n' +
		             '        <span><strong>Remove</strong></span></a>'+
		             '</a></center>'+
		             '</td>';
    		 html += '</tr>';
    		 
    		 $('#manage-material-requests tbody').append(html);
    	 });
     }).catch(function (error) {
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
		currentCapacity : $('#current-capacity').val(),
        siteManager: {
		    employeeId: $('#site-managers').val()
            // don't worry about the other details, it will be taken care of, by the backend.
        }
	}
	
	axios.post(BASE_URL_LOCAL + '/site/add-new-site', data)
    .then(function (response) {
    	$.notify(response.data, "success");
    })
    .catch(function (error) {
        // handle error
        console.log(error);
        $.notify("Site not added", "error");;     
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
    	alert(response.data);
    	$.notify(response.data, "success");
    })
    .catch(function (error) {
        // handle error
        console.log(error);
        $.notify("Item not added", "error");;    
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


/*
 * Shows the information of each site in a tabular format.
 */
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


/*
 * this will fetch all the site managers so that one of them can be assigned to a new site.
 * TODO: make sure to fetch only the managers with no current site assigned to them.
 */
function generateSiteManagersDropdown() {
    axios.get(BASE_URL_LOCAL + '/employee/site-manager')
        .then(response => {
            if (response.data) {
                // we'll put the site managers in a combo box so that,
                // one of them can be chosen.
                // each site manager is identified by his/her employeeId.
                siteManagers = new Array();

                response.data.forEach(siteManager => {
                    siteManagers.push(
                        { value: siteManager.employeeId, html: siteManager.employeeName }
                    );
                });

                console.log(siteManagers);
                // now we map this into a selector field.
                setSelectOptions('site-managers', siteManagers);
            }
        })
        .catch(reject => {
            console.log(reject);
        });

}

/*
 * this will programmatically set the options of a given select-input field.
 *
 * @param selectTagName:
 *      name of the select input tag.
 *
 * @param options:
 *      an array that contains objects where each object has a value and a html.
 *          value -> distinct identifier for each option.
 *          html -> the actual text shown as an option.
 */
function setSelectOptions(selectTagName, options) {
    let selector = $('select[name="site-managers"');

    options.forEach(option => {
        selector.append($("<option>", { value: option.value, html: option.html }));
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
	var btnClass = (status == 1) ? "btn btn-default btn-sm" : "btn btn-primary btn-sm" ;
	var btnText = (status == 1) ? "Approved" : "Approve" ;
	var isDisabled = (status == 1) ? "disabled" : "" ;
	
	
	var html = '<button id="btn-approve-request-material" type="button" title="Approve Button" class="'+btnClass+'" '+isDisabled+'>' +
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

function getBlacklistButton(status){
	var btnClass = (status) ? "btn btn-default btn-sm" : "btn btn-danger btn-sm" ;
	var btnText = (status) ? "Blacklisted" : "Blacklist" ;
	var isDisabled = (status) ? "disabled" : "" ;

	var html = '<button type="button" title="" class="'+btnClass+'" '+isDisabled+'>' +
		    '        <span class="fas fa-ban" aria-hidden="true"></span>' +
		    '        <span><strong>'+btnText+'</strong></span></a>'+
		    '</button>';
	return html;
}

function getBlacklistBadge(status){
	var badgeClass = (status) ? "badge badge-pill badge-danger" : "badge badge-pill badge-primary" ;
	var badgeText = (status) ? "Banned" : "Pending" ;

    return '<h5><span class="'+badgeClass+'">'+badgeText+'</span></h5>';
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
