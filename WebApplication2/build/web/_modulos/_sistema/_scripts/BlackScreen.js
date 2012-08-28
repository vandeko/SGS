var root;
//var nmRoot = "blackscreen";

function BlackScreen(opt,nmRoot){
    init(opt,nmRoot);
}

init = function(opt,nmRoot) {

    var image = 'url(_images/bsbg5.png)';
    root = $("<div id='"+nmRoot+"'>").addClass("bs_root").hide();
    if (opt == "2") {
        image = 'url(../_images/bsbg5.png)'
    } else if (opt == "3") {
        root = $("<div id='"+nmRoot+"'>").addClass("bs_root_winload").hide();
    }

    $("body").prepend(root);
    $('#'+nmRoot).css({
        height: getPageSize()[1]//,
        //backgroundImage: image
    });

}