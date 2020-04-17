function sendDeleteRequest(url , rUrl) {
    $.ajax({
        url: url,
        method: "DELETE",
        success: function () {
            window.location =  rUrl;
        },
        error: function() {
            window.location.reload();
        }
    });
}

function getScreenNamesForMultiplex(){
    var multiplexName=document.getElementById('multiplexName').value;
    location.replace("http://localhost:9000/allot/getScreenNamesForMultiplex?searchParam="+multiplexName);
}

function validateMovieDetails(){
    var movieName=document.getElementById('movieName').value;
    var movieCategory=document.getElementById('movieCategory').value;
    var movieProducer=document.getElementById('movieProducer').value;
    var movieDirector=document.getElementById('movieDirector').value;
    var movieReleaseDate=document.getElementById('movieReleaseDate').value;
    var movieDesc=document.getElementById('movieDesc').value;
    var movieImageUrl=document.getElementById('movieImageUrl').value;
    document.getElementById('movieSubmit').disabled=true;
    hideMovieSpanDetails();
    if(movieName == null || movieName =="" || movieName.length == 0){
        document.getElementById('invalidMovieName').style.display = 'block';
        return false;
    }else if(movieCategory == null || movieCategory =="" || movieCategory.length == 0){
        document.getElementById('invalidMovieCategory').style.display = 'block';
        return false;
    }else if(movieProducer == null || movieProducer =="" || movieProducer.length == 0){
        document.getElementById('invalidMovieProducer').style.display = 'block';
        return false;
    }else if(movieDirector == null || movieDirector =="" || movieDirector.length == 0){
        document.getElementById('invalidMovieDirector').style.display = 'block';
        return false;
    }else if(movieReleaseDate == null || movieReleaseDate =="" || movieReleaseDate.length == 0){
        document.getElementById('invalidMovieReleaseDate').style.display = 'block';
        return false;
    }else if(movieDesc == null || movieDesc =="" || movieDesc.length == 0){
        document.getElementById('invalidMovieDesc').style.display = 'block';
        return false;
    }else if(movieImageUrl == null || movieImageUrl =="" || movieImageUrl.length == 0){
        document.getElementById('invalidMovieImageUrl').style.display = 'block';
        return false;
    }else{
        document.getElementById('movieSubmit').disabled=false;
    }
}

function hideMovieSpanDetails(){
    document.getElementById('invalidMovieName').style.display = 'none';
    document.getElementById('invalidMovieCategory').style.display = 'none';
    document.getElementById('invalidMovieProducer').style.display = 'none';
    document.getElementById('invalidMovieDirector').style.display = 'none';
    document.getElementById('invalidMovieReleaseDate').style.display = 'none';
    document.getElementById('invalidMovieDesc').style.display = 'none';
    document.getElementById('invalidMovieImageUrl').style.display = 'none';

}

function validateMultiplexDetails(){
    var movieName=document.getElementById('multiplexName').value;
    var movieCategory=document.getElementById('multiplexAddress').value;
    var movieProducer=document.getElementById('noOfScreens').value;
    var movieDirector=document.getElementById('ticketCost').value;
    document.getElementById('multiplexSubmit').disabled=true;
    hideMultiplexSpanDetails();
    if(multiplexName == null || multiplexName =="" || multiplexName.length == 0){
        document.getElementById('invalidMultiplexName').style.display = 'block';
        return false;
    }else if(multiplexAddress == null || multiplexAddress =="" || multiplexAddress.length == 0){
        document.getElementById('invalidMultiplexAddress').style.display = 'block';
        return false;
    }else if(noOfScreens == null || noOfScreens =="" || noOfScreens.length == 0){
        document.getElementById('invalidNoOfScreens').style.display = 'block';
        return false;
    }else if(ticketCost == null || ticketCost =="" || ticketCost.length == 0){
        document.getElementById('invalidTicketCost').style.display = 'block';
        return false;
    }else{
        document.getElementById('multiplexSubmit').disabled=false;
    }
}

function hideMultiplexSpanDetails(){
    document.getElementById('invalidMultiplexName').style.display = 'none';
    document.getElementById('invalidMultiplexAddress').style.display = 'none';
    document.getElementById('invalidNoOfScreens').style.display = 'none';
    document.getElementById('invalidTicketCost').style.display = 'none';

}

function validateAllotmentDetails(){
    var multiplexName=document.getElementById('multiplexName').value;
    var movieName=document.getElementById('movieName').value;
    var availableFrom=document.getElementById('availableFrom').value;
    var availableTo=document.getElementById('availableTo').value;
    var screenName=document.getElementById('screenName').value;
    document.getElementById('allotmentSubmit').disabled=true;
    hideAllotmentSpanDetails();
    if(multiplexName == null || multiplexName =="" || multiplexName.length == 0){
        document.getElementById('invalidMultiplexName').style.display = 'block';
        return false;
    }else if(movieName == null || movieName =="" || movieName.length == 0){
        document.getElementById('invalidMovieName').style.display = 'block';
        return false;
    }else if(availableFrom == null || availableFrom =="" || availableFrom.length == 0){
        document.getElementById('invalidAvailableFrom').style.display = 'block';
        return false;
    }else if(availableTo == null || availableTo =="" || availableTo.length == 0){
        document.getElementById('invalidAvailableTo').style.display = 'block';
        return false;
    }else if(screenName == null || screenName =="" || screenName.length == 0){
        document.getElementById('invalidScreenName').style.display = 'block';
        return false;
    }else{
        document.getElementById('allotmentSubmit').disabled=false;
    }
}

function hideAllotmentSpanDetails(){
    document.getElementById('invalidMultiplexName').style.display = 'none';
    document.getElementById('invalidMovieName').style.display = 'none';
    document.getElementById('invalidAvailableFrom').style.display = 'none';
    document.getElementById('invalidAvailableTo').style.display = 'none';
    document.getElementById('invalidScreenName').style.display = 'none';
}

function validatSearchDetails(){
    var searchType=document.getElementById('searchType').checked;
    var searchParam=document.getElementById('searchParam').value;

    document.getElementById('searchSubmit').disabled=true;
    hideSearchSpanDetails();
    if(searchType==false){
        document.getElementById('invalidSearchType').style.display = 'block';
        return false;
    }else if(searchParam == null || searchParam =="" || searchParam.length == 0){
        document.getElementById('invalidSearchParam').style.display = 'block';
        return false;
    }else{
        document.getElementById('searchSubmit').disabled=false;
    }
}
function hideSearchSpanDetails(){
    document.getElementById('invalidSearchParam').style.display = 'none';
    document.getElementById('invalidSearchType').style.display = 'none';
}

function checkdate(input){
    var validformat= /\d{2}-\d{2}-\d{4}/;
    let returnval = false;
    /*if (!validformat.test(input.value))
        alert("Invalid Date Format. Please correct and submit again.")
    else*///Detailed check for valid date ranges
        var monthfield=input.value.split("-")[0];
        var dayfield=input.value.split("-")[1];
        var yearfield=input.value.split("-")[2];
        var dayobj = new Date(yearfield, monthfield-1, dayfield)
        if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
            alert("Invalid Day, Month, or Year range detected. Please correct and submit again.")
        else
            returnval=true;

    if (returnval==false) input.select()
    return returnval;
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
