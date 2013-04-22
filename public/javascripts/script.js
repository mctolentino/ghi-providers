
$(".zebra tbody tr:even").addClass('alternate');

function hideIcon(self) {
    self.style.backgroundImage = 'none';
}

function showstuff(boxid){
   document.getElementById(boxid).style.visibility="visible";
}
 
function hidestuff(boxid){
   document.getElementById(boxid).style.visibility="hidden";
}

function showprocess(boxid){
   document.getElementById(boxid).style.visibility="visible";
}

function hideprocess(boxid){
   document.getElementById(boxid).style.visibility="hidden";
}

function hidesmsverifying(boxid){
   document.getElementById(boxid).style.visibility="hidden";
}

function showauthentication(boxid){
   document.getElementById(boxid).style.visibility="visible";
}

function hideauthentication(boxid){
   document.getElementById(boxid).style.visibility="hidden";
}

function showItem(boxid){
    document.getElementById(boxid).style.display="block";
}

function hideItem(boxid){
    document.getElementById(boxid).style.display="none";
}

