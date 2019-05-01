<%--
  Created by IntelliJ IDEA.
  User: 王成刚
  Date: 2019/4/25
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级注册</title>
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script type="text/javascript">

    var china=new Object();
    china['北京市']=new Array('北京市区','北京市辖区');
    china['上海市']=new Array('上海市区','上海市辖区');
    china['天津市']=new Array('天津市区','天津市辖区');
    china['重庆市']=new Array('重庆市区','重庆市辖区');
    china['河北省'] = new Array('石家庄', '唐山市', '邯郸市', '秦皇市岛', '保市定', '张家市口', '承德市', '廊坊市', '沧州市', '衡水市', '邢台市');
    china['山西省']=new Array('太原市','大同市','阳泉市','长治市','晋城市','朔州市','晋中市','运城市','忻州市','临汾市','吕梁市');
    china['辽宁省']=new Array('沈阳市','大连市','鞍山市','抚顺市','本溪市','丹东市','锦州市','营口市','阜新市','辽阳市','盘锦市','铁岭市','朝阳市','葫芦岛市');
    china['吉林省']=new Array('长春市','吉林市','四平市','辽源市','通化市','白山市','松原市','白城市','延边州','长白山管委会');
    china['黑龙江省']=new Array('哈尔滨市','齐齐哈尔市','大庆市','佳木斯市','牡丹江市','七台河市','双鸭山市','黑河市','鸡西市','伊春市','绥化市','鹤岗市','加格达奇市');
    china['江苏省']=new Array('南京市','苏州市','无锡市','常州市','南通市','扬州市','镇江市','泰州市','盐城市','连云港市','宿迁市','淮安市','徐州市');
    china['浙江省']=new Array('杭州市','宁波市','温州市','嘉兴市','湖州市','绍兴市','金华市','衢州市','舟山市','台州市','丽水市');
    china['安徽省']=new Array('合肥市','芜湖市','蚌埠市','淮南市','马鞍山市','淮北市','铜陵市','安庆市','黄山市','滁州市','阜阳市','宿州市','巢湖市','六安市','亳州市','池州市','宣城市');
    china['福建省']=new Array('福州市','厦门市','莆田市','三明市','泉州市','漳州市','南平市','龙岩市','宁德市');
    china['江西省']=new Array('南昌市','景德镇市','萍乡市','九江市','新余市','鹰潭市','赣州市','吉安市','宜春市','抚州市','上饶市');
    china['山东省']=new Array('济南市','青岛市','淄博市','枣庄市','东营市','烟台市','潍坊市','济宁市','泰安市','威海市','日照市','莱芜市','临沂市','德州市','聊城市','滨州市','菏泽市');
    china['河南省']=new Array('郑州市','开封市','洛阳市','平顶山市','安阳市','鹤壁市','新乡市','焦作市','濮阳市','许昌市','漯河市','三门峡市','南阳市','商丘市','信阳市','周口市','驻马店市');
    china['湖北省']=new Array('武汉市','黄石市','十堰市','荆州市','宜昌市','襄樊市','鄂州市','荆门市','孝感市','黄冈市','咸宁市','随州市');
    china['湖南省']=new Array('长沙市','株洲市','湘潭市','衡阳市','邵阳市','岳阳市','常德市','张家界市','益阳市','郴州市','永州市','怀化市','娄底市');
    china['广东省']=new Array('广州市','深圳市','珠海市','汕头市','韶关市','佛山市','江门市','湛江市','茂名市','肇庆市','惠州市','梅州市','汕尾市','河源市','阳江市','清远市','东莞市','中山市','潮州市','揭阳市','云浮市');
    china['海南省']=new Array('文昌市','琼海市','万宁市','五指山市','东方市','儋州市');
    china['四川省 ']=new Array('成都市','自贡市','攀枝花市','泸州市','德阳市','绵阳市','广元市','遂宁市','内江市','乐山市','南充市','眉山市','宜宾市','广安市','达州市','雅安市','巴中市','资阳市');
    china['贵州省']=new Array('贵阳市','六盘水市','遵义市','安顺市');
    china['云南省']=new Array('昆明市','曲靖市','玉溪市','保山市','昭通市','丽江市','普洱市','临沧市');
    china['陕西省']=new Array('西安市','铜川市','宝鸡市','咸阳市','渭南市','延安市','汉中市','榆林市','安康市','商洛市');
    china['甘肃省']=new Array('兰州市','金昌市','白银市','天水市','嘉峪关市','武威市','张掖市','平凉市','酒泉市','庆阳市','定西市','陇南市');
    china['青海省']=new Array('西宁市');
    china['台湾省'] = new Array('台北市','高雄市','基隆市','台中市','台南市','新竹市','嘉义市');
    china['广西壮族自治区']=new Array('南宁市','柳州市','桂林市','梧州市','北海市','防城港市','钦州市','贵港市','玉林市','百色市','贺州市','河池市','来宾市','崇左市');
    china['内蒙古自治区']=new Array('呼和浩特市','包头市','乌海市','赤峰市','通辽市','鄂尔多斯市','呼伦贝尔市','巴彦淖尔市','乌兰察布市');
    china['西藏自治区']=new Array('拉萨市');
    china['宁夏回族自治区']=new Array('银川市','石嘴山市','吴忠市','固原市','中卫市');
    china['新疆维吾尔自治区']=new Array('乌鲁木齐市','克拉玛依市');
    china['香港特别行政区']=new Array('台北市','高雄市','基隆市','台中市','台南市','新竹市','嘉义市');
    function chinaChange(province, city) {
        var pv, cv;
        var i, ii;
        pv = province.value;
        cv = city.value;
        city.length = 1;
        if (pv == '0') return;
        if (typeof (china[pv]) == 'undefined') return;


        for (i = 0; i < china[pv].length; i++) {
            ii = i + 1;

            city.options[ii] = new Option();
            city.options[ii].text = china[pv][i];
            city.options[ii].value = china[pv][i];
        }
        city.options[0].text = "请选择市区";
    }
</script>
<script type="text/javascript">
    $(function() {
        $("#check").click(function () {
            var province = $("#sel").val();
            province += $("#city").val();
            var sc = $("#schoolName").val();
            var cl = $("#className").val();
            var desc = $("#desc").val();
            alert("pro: "+province+"   sc: "+sc+"   cl: "+cl + "desc" + desc);
            $.ajax({
                contentType: "application/x-www-form-urlencoded;chartset=utf-8",
                scriptCharset: 'utf-8',
                url: "/isExistClass",
                type: "POST",
                dataType: "JSON",
                data: {
                    "schoolAddress": province,
                    "schoolName": sc,
                    "className": cl
                },
                success: function (ops) {
                    console.log(ops);
                    if (ops == "1") {
                        alert("请选择地址和填写创建信息");
                    } else if (ops == "2") {
                        alert("该班级存在");
                    } else if (ops == "3") {
                        alert("可以创建改班级");
                    } else {
                       alert("未知错误");
                    }
                },
                error: function () {
                    alert("请求失败");
                }
            });
        })
    })
</script>
    <script type="text/javascript">
        $(function() {
            $("#create").click(function () {
                var province = $("#sel").val();
                province += $("#city").val();
                var sc = $("#schoolName").val();
                var cl = $("#className").val();
                var desc = $("#desc").val();
                alert("pro: "+province+"   sc: "+sc+"   cl: "+cl + "desc" + desc);
                $.ajax({
                    contentType: "application/x-www-form-urlencoded;chartset=utf-8",
                    scriptCharset: 'utf-8',
                    url: "/createClass",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        "schoolAddress": province,
                        "schoolName": sc,
                        "className": cl,
                        "classDesc": desc
                    },
                    success: function (ops) {
                        console.log("状态吗"+ops.status);
                        if (ops == "0") {
                            alert("请选择地址和填写创建信息");
                        } else if (ops == "1") {
                            alert("该班级已经存在")
                        } else if (ops == "2") {
                            alert("你不是老师,不能进行创建班级");
                        } else if (ops == "3") {
                            alert("班级创建成功，但是绑定班级失败，请到主页面添加代课班级")
                        }else if (ops == "36") {
                            alert("创建成功,并且绑定了班级");
                                window.location.href="/login";
                        } else if(ops == "4") {
                            alert("创建失败，请重新登陆创建");
                        } else {
                            alert("未知身份");
                        }
                    },
                    error: function () {
                        alert("请求失败");
                    }
                });
            })
        })
    </script>
</head>
<body>
<select id="sel" onchange="chinaChange(this,document.getElementById('city'))" style=" width:10%; height:30px;line-height:30px; ">
    <option value ="请选择省份">请选择省份</option>
    <option value ="北京市">北京市 </option>
    <option value ="天津市">
        天津市 </option><option value ="上海市">
    上海市 </option><option value ="重庆市">
    重庆市 </option><option value ="河北省">
    河北省 </option><option value ="山西省">
    山西省 </option><option value ="辽宁省">
    辽宁省 </option><option value ="吉林省">
    吉林省 </option><option value ="黑龙江省">
    黑龙江省</option><option value ="江苏省">
    江苏省 </option><option value ="浙江省">
    浙江省 </option><option value ="安徽省">
    安徽省 </option><option value ="福建省">
    福建省 </option><option value ="江西省">
    江西省 </option><option value ="山东省">
    山东省 </option><option value ="河南省">
    河南省 </option><option value ="湖北省">
    湖北省 </option><option value ="湖南省">
    湖南省 </option><option value ="广东省">
    广东省 </option><option value ="海南省">
    海南省 </option><option value ="四川省">
    四川省 </option><option value ="贵州省">
    贵州省 </option><option value ="云南省">
    云南省 </option><option value ="陕西省">
    陕西省 </option><option value ="甘肃省">
    甘肃省 </option><option value ="青海省">
    青海省 </option><option value ="台湾省">
    台湾省 </option><option value ="广西壮族自治区">
    广西壮族自治区</option><option value ="内蒙古自治区">
    内蒙古自治区</option><option value ="西藏自治区">
    西藏自治区</option><option value ="宁夏回族自治区">
    宁夏回族自治区 </option><option value ="新疆维吾尔自治区">
    新疆维吾尔自治区</option><option value ="香港特别行政区">
    香港特别行政区</option><option value ="澳门特别行政区">
    澳门特别行政区</option>
</select><br>
<select name="city" id="city" style=" width:10%; height:30px;line-height:30px; ">
    <option value ="请选择市区">请选择市区</option>
</select>
学校名称：<input id="schoolName" type="text" style="width:10%; height:30px;line-height:30px;"><br>
班级名称：<input id="className" type="text" style="width:10%; height:30px;line-height:30px;"><br>
班级描述：<input id="desc" type="text" style="width:20%; height:50px;line-height:50px;">
<%--<input type="button" value="检测是否存在班级" id="check" name="check"><br>--%>
<input type="button" value="创建班级" id="create" name="create" style="width: 50px;height: 50px;">
</body>
</html>
