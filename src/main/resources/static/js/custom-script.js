/**
 * This JS file includes all Procurement Staff related Scripts
 */


/* * * * *     Global Variables     * * * * */
let BASE_URL_LOCAL = 'http://localhost:8080';
let USER_INFO = 'user-info'
let CURRENT_URL = window.location.href;

/* * * * *     Event Triggers     * * * * */
$('#btn-logout').on('click', function (e) {
    e.preventDefault();
    localStorage.removeItem(USER_INFO);
    window.location.href = "index.html";
});

$('#btn-add-new-site').on('click', function (e) {
    e.preventDefault();
    addNewSite();
});




/* * * * *     Page Activities     * * * * */
if (CURRENT_URL.includes('manage-material-requests')) {
    console.log("You are on Manage Material Requests page")
    
    
    loadRequestedMaterialTable()
    
}

if (CURRENT_URL.includes('manage-black-list')) {
    console.log("You are on Manage Blacklist page")
}

if (CURRENT_URL.includes('manage-sites')) {
    console.log("You are on Manage Sites page")
    getAllSites();
}

if (CURRENT_URL.includes('view-ratings')) {
    console.log("You are on View Ratings page")
}

if (CURRENT_URL.includes('add-new-site')) {
    console.log("You are on add new site page")
}


function loadRequestedMaterialTable(){
	 axios.get(BASE_URL_LOCAL + '/requestmaterial/')
     .then(function (response) {
    	 console.log(response)
    	 response.data.forEach(item => {
    	 
    		 $('#manage-material-requests tbody').append('<tr>' +
            '<td>'+item.orderId+'</td>' +
            '<td class="nr-fid" scope="row">' + item.requestedPerson + '</td>' +
            '<td >' + item.siteId + '</td>' +
            '<td >' + getItemList(item.items) + '</td>' +
            '<td>' + item.requestedDate + '</td>' +
            '<td>' + item.isImmediated + '</td>' +
            '<td>' + item.isProcumentApproved + '</td>' +
            '<td><center>' +
            '<a href="#" title="" class="btn btn-primary btn-sm">\n' +
            '        <span class="far fa-eye" aria-hidden="true"></span>\n' +
            '        <span><strong>Edit</strong></span></a>'+
            '</a></center>' +
            '</td>' +
            '<td><center>' +
            '<a href="#" title="" class="btn btn-danger btn-sm">\n' +
            '        <span class="far fa-eye" aria-hidden="true"></span>\n' +
            '        <span><strong>Remove</strong></span></a>'+
            '</a></center>' +
            '</td>' +
            '</tr>');
    	 });
     })
     .catch(function (error) {
         // handle error
         console.log(error);
     });
}


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

function addNewSite(){
	axios.get(BASE_URL_LOCAL + '/requestmaterial/')
    .then(function (response) {
    	
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

function getAllSites(){
	axios.get(BASE_URL_LOCAL + '/site/')
    .then(function (response) {
   	 console.log(response)
   	 response.data.forEach(item => {
   		 
   		 $('#manage-sites tbody').append('<tr>' +

           '<td>'+item.siteId+'</td>' +
           '<td class="nr-fid" scope="row">' + item.siteName + '</td>' +
           '<td >' + item.address + '</td>' +
           '<td >' + getItemList(item.items) + '</td>' +
           '<td>' + item.storageCapacity + '</td>' +
           '<td>' + item.currentCapacity + '</td>' +
           '<td><center>' +
           '<a href="#" title="" class="btn btn-primary btn-sm">\n' +
           '        <span class="far fa-eye" aria-hidden="true"></span>\n' +
           '        <span><strong>Edit</strong></span></a>'+
           '</a></center>' +
           '</td>' +
           '<td><center>' +
           '<a href="#" title="" class="btn btn-danger btn-sm">\n' +
           '        <span class="far fa-eye" aria-hidden="true"></span>\n' +
           '        <span><strong>Remove</strong></span></a>'+
           '</a></center>' +
           '</td>' +
           '</tr>');
   	 });
    })
    .catch(function (error) {
        // handle error
        console.log(error);
    });
}

/* * * * *     Reusable functions     * * * * */
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}