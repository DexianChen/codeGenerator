/**
 * 该插件用于进行开发测试
 * @param obj JQuery对象
 * @returns {string} 当前元素的html片段
 * @description
 * 开发中，经常需要进行DOM元素选择后才能对其进行操作，但往往会不知道选择的是什么DOM元素。使用该插件，将元素输入即可返回
 * 元素的html片段，方便测试，提高开发效率
 */
returnObjectInfo = function (obj) {
   try {
       if (obj != null && obj != "") {
           //将对象或者数组一律转换为数组，就无需进行数组还是对象的判断了，如果对象为非DOM对象因不能调用toArray()而报错
           var array = obj.toArray();
           return getObjectInfo(array);
       }else {
           return "所选对象为空";
       }
   }catch (e) {
       return "所选对象为非DOM对象，请检查！"
   }
};

getObjectInfo = function(array){
    var str = "";
    $.each(array, function (index, value) {
        //获取数组中的元素的html片段
        str += $(value).prop("outerHTML") + "\r\n";
    });
    return str;
};