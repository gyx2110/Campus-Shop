$(function(){

    // 获取shopId
    var shopId = getQueryString("shopId");
    // 商铺管理的url
    if(shopId == '') {
    	window.location.href = '/ssm/shopadmin/shoplist';
    }
    var shopInfoUrl = '/ssm/shopadmin/getshopmanagementinfo?shopId=' + shopId;

    $.getJSON(shopInfoUrl,function (data) {
        // 如果后台返回redirect=true,则跳转后台到设置的url
        if(data.redirect){
            window.location.href = data.url;
        }else{
            // 如果后台返回redirect=false，则设置shopId并给 按钮设置超链接属性（即编辑商铺）
            if (data.shopId != undefined && data.shopId != null){
                shopId = data.shopId;
            }
            $('#shopInfo').attr('href','/ssm/shopadmin/shopoperation?shopId=' + shopId);
            $('#productCategory').attr('href','/ssm/shopadmin/productcategorymanagement');
        }
    });


});