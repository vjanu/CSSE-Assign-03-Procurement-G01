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

$('#btn-edit-site').on('click', function (e) {
	e.preventDefault();
	updateSiteDetails();
});



/**
 * Material request approve click
 * this click cannot be undone
 */
$(document).on('click', '#manage-material-requests .btn-primary', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var oid = $(this).closest("tr").find(".nr-oid").text();
	console.log(oid);
	var r = confirm("Are you sure? This action cannot be undone");
	if (r == true) {
		approveRequestedMaterial(oid);
	}
});

/**
 * Click event in material request 
 */
$(document).on('click', '#manage-material-requests .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var oid = $(this).closest("tr").find(".nr-oid").text();
	console.log(oid);
	var r = confirm("Are you sure want to delete this request? This action cannot be undone");
	if (r == true) {
		removeMaterialRequest(oid);
	}
});

/**
 * Delete Click event in manage-sites
 */
$(document).on('click', '#manage-sites .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var siteId = $(this).closest("tr").find(".nr-siteId").text();
	console.log(siteId);
	var r = confirm("Are you sure want to delete this Site? This action cannot be undone");
	if (r == true) {
		removeSite(siteId);
	}
});

/**
 * Click event in manage-constructor-black-list
 */
$(document).on('click', '#manage-constructor-black-list .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var cid = $(this).closest("tr").find(".nr-cid").text();
	console.log(cid);
	var r = confirm("Are you sure want to blacklist this constructor?");
	if (r == true) {
		blacklistConstructor(cid, true);
	}
});

$(document).on('click', '#manage-constructor-black-list .btn-warning', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var cid = $(this).closest("tr").find(".nr-cid").text();
	console.log(cid);
	var r = confirm("Are you sure want to Unbanned this constructor?");
	if (r == true) {
		blacklistConstructor(cid, false);
	}
});

/**
 * Click event in manage-supplier-black-list
 */
$(document).on('click', '#manage-supplier-black-list .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var sid = $(this).closest("tr").find(".nr-sid").text();
	console.log(sid);
	var r = confirm("Are you sure want to blacklist this Supplier?");
	if (r == true) {
		blacklistSupplier(sid, true);
	}
});


$(document).on('click', '#manage-supplier-black-list .btn-warning', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var sid = $(this).closest("tr").find(".nr-sid").text();
	console.log(sid);
	var r = confirm("Are you sure want to Unbanned this Supplier?");
	if (r == true) {
		blacklistSupplier(sid, false);
	}
});


/**
 * Click event in site-item-table
 */
$(document).on('click', '#site-item-table .btn-danger', function (e) {
	e.preventDefault();
	e.stopPropagation();
	var itemId = $(this).closest("tr").find(".nr-itemId").text();
	console.log(itemId);
	var r = confirm("Are you sure want to remove this item from this site? This action cannot be undone");
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









/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('manage-material-requests')) {
	loadRequestedMaterialTable();
}

if (CURRENT_URL.includes('manage-supplier-blacklist')) {
	loadAllSuppliers()
}

if (CURRENT_URL.includes('manage-constructor-blacklist')) {
	loadAllConstructors()
}

if (CURRENT_URL.includes('manage-sites')) {
	loadAllSites();
}

if (CURRENT_URL.includes('view-supplier-ratings')) {
	loadSupplierRatings();
}

if (CURRENT_URL.includes('view-constructor-ratings')) {
	loadConstructorRatings();
}

if (CURRENT_URL.includes('add-new-site')) {
	generateItemSelectDropdown();
	generateSiteManagersDropdown('site-managers');
}

if (CURRENT_URL.includes('add-new-item')) {
	generateCategoryDropdown();
}

if (CURRENT_URL.includes('edit-site')) {
	populateSiteDetails();
	populateSiteItems();
	generateSiteManagersDropdown('site-managers');
}

if (CURRENT_URL.includes('procurement-staff-dashboard')) {
	// get();
}


/* * * * * FUNCTION * * * * */
function populateSiteItems() {
	if (CURRENT_URL.includes('#')) {
		let siteId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.length);
		axios.get(BASE_URL_LOCAL + '/site/siteitem/' + siteId).then(function (response) {
			if (response.data) {
				console.log(response);
				response.data.forEach(item => {
					var html = '<tr>';
					html += '<td class="text-center">' + item.itemId + '</td>';
					html += '<td class="text-center">' + item.itemName + '</td>';
					html += '<td class="text-center">' + item.quantity + '</td>';
					html += '</tr>';

					$('#site-item-list tbody').append(html);
				});


			}
		}).catch(function (error) {
			console.log(error);
		});
	}

}

function populateSiteDetails() {
	if (CURRENT_URL.includes('#')) {
		let siteId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.length);
		$("#site-id").val(siteId);
		axios.get(BASE_URL_LOCAL + '/site/' + siteId).then(function (response) {
			if (response.data) {
				console.log(response);
				$('#site-name').val(response.data.siteName);
				$('#address').val(response.data.address);
				$('#storage-capacity').val(response.data.storageCapacity);
				$('#current-capacity').val(response.data.currentCapacity);
				if (response.data.siteManager.employeeId != null) {
					$('#site-managers').append($("<option></option>")
						.attr("value", response.data.siteManager.employeeId)
						.text(response.data.siteManager.employeeName));
				}
			}
		}).catch(function (error) {
			console.log(error);
		});
	}
}


function blacklistSupplier(sid, isBanned) {
	let data = {
		"isBanned": isBanned
	}
	axios.put(BASE_URL_LOCAL + '/supplier/update/' + sid, data)
		.then(function (response) {
			console.log(response);
			$.notify((isBanned) ? sid + " Successfully Blacklisted" : sid + " Successfully Unbanned", "success");
			loadAllSuppliers();
		}).catch(function (error) {
			console.log(error);
		});
}



function blacklistConstructor(cid, isBanned) {
	let data = {
		"isBanned": isBanned
	}
	axios.put(BASE_URL_LOCAL + '/employee/update/' + cid, data)
		.then(function (response) {
			console.log(response);
			$.notify((isBanned) ? cid + " Successfully Blacklisted" : cid + " Successfully Unbanned", "success");
			loadAllConstructors();
		}).catch(function (error) {
			console.log(error);
		});
}


function removeMaterialRequest(rid) {
	axios.delete(BASE_URL_LOCAL + '/requestmaterial/remove/' + rid)
		.then(function (response) {
			console.log(response);
			if (response.data) {
				$.notify(rid + " Removed Successfully", "success");
				loadRequestedMaterialTable();
			} else {
				$.notify(rid + " Not Removed", "error");
			}
		}).catch(function (error) {
			console.log(error);
		});
}

function removeSite(siteId) {
	axios.delete(BASE_URL_LOCAL + '/site/' + siteId)
		.then(function (response) {
			console.log(response);
			if (response.data) {
				$.notify(siteId + " Removed Successfully", "success");
				loadAllSites();
			} else {
				$.notify(siteId + " Not Removed", "error");
			}
		}).catch(function (error) {
			console.log(error);
		});
}


function approveRequestedMaterial(oid) {
	let data = {
		"isProcumentApproved": true
	}
	axios.put(BASE_URL_LOCAL + '/requestmaterial/update/' + oid, data)
		.then(function (response) {
			console.log(response);
			if (response.data) {
				$.notify(oid + " Approved Successfully", "success");
				loadRequestedMaterialTable();
			} else {
				$.notify(oid + " Not Approved", "error");
			}
		}).catch(function (error) {
			console.log(error);
		});
}


function loadSupplierRatings() {
	axios.get(BASE_URL_LOCAL + '/ratings/supplier-ratings').then(function (response) {
		if (response.data) {
			console.log(response)
			response.data.forEach(item => {
				var html = '<tr>';
				html += '<td class="text-center">' + item.purchaseOrderReference + '</td>';
				html += '<td class="text-center">' + item.supplierName + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.deliveryEfficiency) + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.supportiveness) + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.workOnTime) + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.overallRate) + '</td>';
				html += '<td class="text-center">' + item.feedback + '</td>';
				html += '</tr>';

				$('#view-supplier-ratings tbody').append(html);
			});
		}
	}).catch(function (error) {
		console.log(error);
	});
}


function loadConstructorRatings() {
	axios.get(BASE_URL_LOCAL + '/ratings/constructor-ratings').then(function (response) {
		if (response.data) {
			response.data.forEach(item => {
				var html = '<tr>';
				html += '<td class="text-center">' + item.constructorId + '</td>';
				html += '<td class="text-center">' + item.constructorName + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.deliveryEfficiency) + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.supportiveness) + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.workOnTime) + '</td>';
				html += '<td class="text-center">' + generateRatingStarts(item.overallRate) + '</td>';
				html += '<td class="text-center">' + item.feedback + '</td>';
				html += '</tr>';

				$('#view-constructor-ratings tbody').append(html);
			});
		}
	}).catch(function (error) {
		console.log(error);
	});
}

/**
 * This function load all suppliers
 */
function loadAllSuppliers() {
	axios.get(BASE_URL_LOCAL + '/supplier/')
		.then(function (response) {
			console.log(response)
			$("#manage-supplier-black-list tbody").empty();

			response.data.forEach(item => {
				var html = '<tr>';
				html += '<td class="nr-sid"><center>' + item.supplierId + '</center></td>';
				html += '<td class="text-center">' + item.supplierName + '</td>';
				html += '<td class="text-center">' + item.email + '</td>';
				html += '<td class="text-center">' + item.phone + '</td>';
				html += '<td class="text-center">' + item.address + '</td>';
				html += '<td class="text-center">' + getBlacklistBadge(item.isBanned) + '</td>';
				html += '<td><center>' + getBlacklistButton(item.isBanned, false) + '</td>';
				html += '</tr>';

				$('#manage-supplier-black-list tbody').append(html);
			});
		}).catch(function (error) {
			// handle error
			console.log(error);
		});

}

/**
 * get all constructors for blacklist purpose
 */
function loadAllConstructors() {
	axios.get(BASE_URL_LOCAL + '/employee/constructor/')
		.then(function (response) {
			console.log(response)
			$("#manage-constructor-black-list tbody").empty();

			response.data.forEach(item => {
				var html = '<tr>';
				html += '<td class="nr-cid"><center>' + item.employeeId + '</center></td>';
				html += '<td class="text-center">' + item.employeeName + '</td>';
				html += '<td class="text-center">' + item.email + '</td>';
				html += '<td class="text-center">' + item.phone + '</td>';
				html += '<td class="text-center">' + item.address + '</td>';
				html += '<td class="text-center">' + getBlacklistBadge(item.isBanned) + '</td>';
				html += '<td class="text-center">' + getBlacklistButton(item.isBanned, false) + '</td>';
				html += '</tr>';

				$('#manage-constructor-black-list tbody').append(html);
			});
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
}

/**
 * here get all metarial request that are approved by site manager
 */
function loadRequestedMaterialTable() {
	axios.get(BASE_URL_LOCAL + '/requestmaterial/sitemanager-approved/')
		.then(function (response) {
			console.log(response);
			$("#manage-material-requests tbody").empty();
			response.data.forEach(item => {

				var html = '<tr>';
				html += '<td class="nr-oid" scope="row"><center>' + item.requestId + '</center></td>';
				html += '<td class="text-center">' + item.requestedPerson + '</td>';
				html += '<td class="text-center">' + item.siteId + '</td>';
				html += '<td >' + getItemList(item.items) + '</td>';
				html += '<td class="text-center">' + item.requestedDate + '</td>';
				html += '<td class="text-center">' + getImmediateButton(item.isImmediated) + '</td>';
				html += '<td class="text-center">' + isViolation(item.items) + '</td>';
				html += '<td class="text-center">' + requestStatus(item.isProcumentApproved) + '</td>';
				html += '<td class="text-center">' + getApprovedButton(item.isProcumentApproved) + '</td>';
				html += '<td><center>' +
					'<a href="#" title="" class="btn btn-success btn-sm">\n' +
					'        <span class="fas fa-check-double" aria-hidden="true"></span>\n' +
					'        <span><strong>Notify</strong></span></a>' +
					'</a></center>' +
					'</td>';
				html += '<td><center>' +
					'<a href="#" title="" class="btn btn-danger btn-sm">\n' +
					'        <span class="far fa-trash-alt" aria-hidden="true"></span>\n' +
					'        <span><strong>Remove</strong></span></a>' +
					'</a></center>' +
					'</td>';
				html += '</tr>';

				$('#manage-material-requests tbody').append(html);
			});
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
}

function updateSiteDetails() {
	if (CURRENT_URL.includes('#')) {
		let siteId = CURRENT_URL.substr(CURRENT_URL.indexOf('#') + 1, CURRENT_URL.length);
		let data = {
			siteId: siteId,
			siteName: $('#site-name').val(),
			address: $('#address').val(),
			storageCapacity: $('#storage-capacity').val(),
			currentCapacity: $('#current-capacity').val(),
			siteManager: {
				employeeId: $('#site-managers').val()
				// don't worry about the other details, it will be taken care of, by the backend.
			}
		}

		axios.put(BASE_URL_LOCAL + '/site/' + siteId, data)
			.then(function (response) {
				console.log(response);
				if (response.data) {
					$.notify(siteId + " updated successfully", "success");
				} else {
					$.notify(siteId + " not updated", "error");
				}
			})
			.catch(function (error) {
				// handle error
				console.log(error);
				$.notify("Site not Updated", "error");;
			});

	}
}

/**
 * This function will add new site to the system
 */
function addNewSite() {
	let storedItems = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];
	var itemList = [];
	for (var i of storedItems) {
		let data = {
			itemId: i.itemId,
			quantity: i.itemQty
		}
		itemList.push(data);
	}

	let data = {
		siteName: $('#site-name').val(),
		address: $('#address').val(),
		items: itemList,
		storageCapacity: $('#storage-capacity').val(),
		currentCapacity: $('#current-capacity').val(),
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
function addNewItem() {
	let data = {
		itemId: $('#item-id').val(),
		itemName: $('#item-name').val(),
		categoryId: $('#item-category-id').val(),
		price: $('#item-price').val(),
		deliveryInformation: $('#delivery-information').val(),
		isRestrictedItem: $('#restricted-item').val()
	}
	axios.post(BASE_URL_LOCAL + '/item/add-new-item', data)
		.then(function (response) {
			alert(response.data);
			$.notify(response.data, "success");
		})
		.catch(function (error) {
			// handle error
			console.log(error);
			$.notify("Item not added", "error");
		});
}


function addItemToSite() {
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

	$('#site-item-table tbody').append(html);
}


/*
 * Shows the information of each site in a tabular format.
 */
function loadAllSites() {
	axios.get(BASE_URL_LOCAL + '/site/')
		.then(function (response) {
			$("#manage-sites tbody").empty();
			response.data.forEach(item => {
				let tr =
					'<tr>' +
					'<td class="nr-siteId"><center>' + item.siteId + '</center></td>' +
					'<td class="text-center">' + item.siteName + '</td>' +
					'<td class="text-center">' + item.address + '</td>' +
					'<td>' + getItemList(item.items) + '</td>' +
					'<td class="text-center">' + item.storageCapacity + '</td>' +
					'<td class="text-center">' + item.currentCapacity + '</td>' +
					'<td class="text-center">' + getSiteManagerInfo(item.siteManager) + '</td>' +
					'<td class="text-center">' +
					'<a href="edit-site.html#' + item.siteId + '" title="" class="btn btn-primary btn-sm">' +
					'        <span class="fas fa-edit" aria-hidden="true"></span> Edit' +
					'</a></td>' +
					'<td class="text-center">' +
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

/**
 * This fucntion return assigned site manager name
 * if site manager not assigned it return (Not Assigned)
 * @param {*} siteManager 
 */
function getSiteManagerInfo(siteManager) {
	return (siteManager.employeeName != null) ? siteManager.employeeName : '<span style="color:red"><i>(Not Assigned)</i></span>';
}


/*
 * this will fetch all the site managers so that one of them can be assigned to a new site.
 * 
 * @param selectTagName:
 *      name of the select input tag.
 *
 * TODO: make sure to fetch only the managers with no current site assigned to them.
 */
function generateSiteManagersDropdown(selectTagName) {
	axios.get(BASE_URL_LOCAL + '/employee/site-manager/unassign')
		.then(response => {
			if (response.data) {
				// we'll put the site managers in a combo box so that,
				// one of them can be chosen.
				// each site manager is identified by his/her employeeId.
				siteManagers = new Array();

				response.data.forEach(siteManager => {
					siteManagers.push({
						value: siteManager.employeeId,
						html: siteManager.employeeName
					});
				});

				console.log(siteManagers);
				// now we map this into a selector field.
				setSelectOptions(selectTagName, siteManagers);
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
	let selector = $('select[name="' + selectTagName + '"');

	options.forEach(option => {
		selector.append($("<option>", {
			value: option.value,
			html: option.html
		}));
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
function getItemList(items) {
	var html = '<ul>';

	items.forEach(item => {
		html += '<li>' + item.itemId + ' - ' + item.itemName + '</li>';
	});
	html += '</ul>';
	return html;
}

function getRequestedItemList(items) {
	var html = '<ul>';
	for (var key in items) {
		if (items.hasOwnProperty(key)) {
			html += '<li>' + key + ' - ' + items[key] + '</li>';
		}
	}
	html += '</ul>';
	return html;
}


function getApprovedButton(status) {
	var btnClass = (status == 1) ? "btn btn-default btn-sm" : "btn btn-primary btn-sm";
	var btnText = (status == 1) ? "Approved" : "Approve";
	var isDisabled = (status == 1) ? "disabled" : "";


	var html = '<button id="btn-approve-request-material" type="button" title="Approve Button" class="' + btnClass + '" ' + isDisabled + '>' +
		'        <span class="fa fa-check" aria-hidden="true"></span>' +
		'        <span><strong>' + btnText + '</strong></span></a>' +
		'</button>';
	return html;
}

function getImmediateButton(status) {
	var badgeClass = (status == 1) ? "badge badge-pill badge-danger" : "badge badge-pill badge-warning";
	var badgeText = (status == 1) ? "Yes" : "No";

	var html = '<h4><span class="' + badgeClass + '">' + badgeText + '</span></h4>';
	return html;
}

function getBlacklistButton(isBanned, isbtnDisabled) {

	var btnClass = (isBanned) ? ((isbtnDisabled) ? "btn btn-default btn-sm" : "btn btn-warning btn-sm") : "btn btn-danger btn-sm";
	var btnText = (isBanned) ? ((isbtnDisabled) ? "Blacklisted" : "Unbanned") : "Blacklist";
	var isDisabled = (isBanned) ? ((isbtnDisabled) ? "disabled" : "") : "";

	var html = '<button type="button" title="" class="' + btnClass + '" ' + isDisabled + '>' +
		'        <span class="fas fa-ban" aria-hidden="true"></span>' +
		'        <span><strong>' + btnText + '</strong></span></a>' +
		'</button>';
	return html;
}

function getBlacklistBadge(status) {
	var badgeClass = (status) ? "badge badge-pill badge-danger" : "badge badge-pill badge-primary";
	var badgeText = (status) ? "Banned" : "Active";

	return '<h5><span class="' + badgeClass + '">' + badgeText + '</span></h5>';
}

function generateCategoryDropdown() {
	axios.get(BASE_URL_LOCAL + '/category/')
		.then(function (response) {
			console.log(response.data)
			var html = '<select class="form-control" id="item-category-id">';

			response.data.forEach(supplier => {
				html += '<option value="' + item.categoryId + '">' + item.categoryName + '</option>';
			});
			html += '</select>';
			console.log(html)
			$('#cat-select').append(html);
		}).catch(function (error) {
			// handle error
			console.log(error);
		});
}

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

/**
 * This function generate the rating stars
 * @param {*} rateValue 
 */
function generateRatingStarts(rateValue) {
	var html = '<div>';
	for (i = 0; i < 5; i++) {
		let isChecked = (rateValue < 1) ? "" : "checked";
		console.log(isChecked + " | " + rateValue);
		html += '<span class="fa fa-star ' + isChecked + '"></span>';
		rateValue--;
	}
	html += '</div>';
	return html;
}


/**
 * Check whether item violate the company policies
 * @param {*} items 
 */
function isViolation(items) {
	let isCompanyPoliciesViolated = false;
	let totalCost = 0;
	items.forEach(item => {
		if (item.isRestrictedItem) {
			isCompanyPoliciesViolated = true;
		}
		totalCost += item.price * item.quantity; 
	});

	if(totalCost > 100000){
		isCompanyPoliciesViolated = true;
	}

	var badgeClass = (isCompanyPoliciesViolated) ? "badge badge-pill badge-danger" : "badge badge-pill badge-primary";
	var badgeText = (isCompanyPoliciesViolated) ? "Yes" : "No";

	return '<h5><span class="' + badgeClass + '">' + badgeText + '</span></h5>';
}

/**
 * Get the status of material request
 * @param {*} isProcumentApproved 
 */
function requestStatus(isProcumentApproved){
	var badgeClass = (isProcumentApproved) ? "badge badge-pill badge-success" : "badge badge-pill badge-warning";
	var badgeText = (isProcumentApproved) ? "Approved" : "Pending";

	return '<h5><span class="' + badgeClass + '">' + badgeText + '</span></h5>';
}