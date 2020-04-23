var API = {
    config: {
        //server:true
    }
};
(function (window) {

    API.httpserver = "http://localhost:8080/logistics_base_Web_exploded";

    //线上服务器
    if (API.config.server) {
        API.httpserver = "http://localhost:8080/logistics_base_Web_exploded";
        API.imgserver = "";
    }

    API.timer = 10000;
    /*ajax */
    API.ajax = function (path, data) {

        if (!data) {
            data = {};
        }
        var url = API.httpserver + path + ".html"
        if (path.indexOf('http://') == 0) {
            url = path;
        }

        var result = {
            success: function () {
            },
            error: function () {
            },
            exception: function () {
            }
        }

        $.ajax({
            type: 'POST',//提交 方式

            async: true, //默认为true 异步

            cache: false,//缓存

            url: url,//提交url

            data: data,//提交数据  json 格式

            success: function (data) {
                if (data.state == "ok") {
                    result.success(data)
                } else {
                    result.error(data)
                }
            },

            dataType: 'json',

            error: function (data) {
                result.exception(data)
            }

        });

        return {
            success: function (callback) {
                result.success = callback;
                return this;
            },
            error: function (callback) {
                result.error = callback;
                return this;
            },
            exception: function (callback) {
                result.exception = callback;
                return this;
            }
        }
    }

    API.getUserInfo = function () {
        var user = API.getStore("userinfo");
        if (user) {
            return JSON.parse(user);
        } else {
            return {};
        }
    }


    //h5 local store
    // value 为字符串 或者 JSON格式
    API.setStore = function (key, value) {
        localStorage.setItem(key, value);
    }
    //取
    API.getStore = function (key) {
        return localStorage.getItem(key);
    }
    //删
    API.clearStore = function (key) {
        localStorage.removeItem(key);
    }
    //清除所有
    API.clearAllStore = function () {
        localStorage.clear();
    }

    //获取页面传值 参数
    API.getParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    API.settingHeard = function (heardName) {
        var str = '<div class="pull-left">' +
            '<a href="javascript:history.go(-1);window.loaction.reload()">' +
            '<span class="glyphicon glyphicon-menu-left"></span>返回' +
            '</a>' +
            '</div>' +
            '<h4 class="pull-left">' + heardName + '</h4>' +
            '<div class="pull-right">' +
            '<img src="' + API.httpserver + '/images/china_mobile.png">' +
            '</div>';

        $("#weixin_header").html(str)
    }

    //对于cookie的操作

    //读取cookie
    API.getCookie = function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }
    //删除cookie
    API.delCookie = function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = API.getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }

    //清除所有cookie
    API.clearCookie = function () {
        var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
        if (keys) {
            for (var i = keys.length; i--;)
                document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
        }
    }

    //设置cookie带过期时间
    API.setCookie = function (name, value, time) {
        var strsec = (time != undefined && time != "") ? getsec(time) : 30 * 24 * 60 * 60 * 1000;
        var exp = new Date();
        exp.setTime(exp.getTime() + strsec * 1);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    }

    function getsec(str) {
        var str1 = str.substring(1, str.length) * 1;
        var str2 = str.substring(0, 1);
        if (str2 == "s") {
            return str1 * 1000;
        } else if (str2 == "h") {
            return str1 * 60 * 60 * 1000;
        } else if (str2 == "d") {
            return str1 * 24 * 60 * 60 * 1000;
        }
    }

    API.isNull = function (e) {
        if (e === null)
            return true;
        if (e === "")
            return true;
        if (e === "undefined")
            return true;
        if (e === undefined)
            return true;
        return false;
    }

    API.setHeight = function (id, h) {
        var set_h = window.screen.height + h;
        $("#" + id).css("height", set_h + "px");
    }

    API.setWidth = function (id, h) {
        var set_h = window.screen.width + h;
        $("#" + id).css("width", set_h + "px");
    }

    API.checkLogin = function () {
        var userInfo = API.getUserInfo();
        var accountName = userInfo.accountName;
        if (API.isNull(accountName))
            window.document.location.href = API.httpserver + "/app/login.html";
    }

    /**
     * 记录当前连接
     * 用途：退出后再次进入直达
     */
    API.setCurrentVisitUrl = function () {
        var url = window.location.href;
        if (url.indexOf("login") == -1 && url.indexOf("index") == -1)
            API.setStore("CVU", url);
    }

    API.getCVU = function () {
        return API.getStore("CVU");
    }

    API.setCurrentVisitUrl();
})(window)
