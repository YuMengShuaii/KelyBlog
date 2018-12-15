/**
 * Created by Administrator on 2017/7/2.
 */
var openApp = new Vue({
        el: '#openApp',
        data: {
            message: '打开App',
        },
        methods: {
            openclint:function(){
                window.location="javashop://m.javashop.com/goods/detail"
                var ua = navigator.userAgent.toLowerCase();
                var startTime = Date.now();
                var downurl = ua.indexOf('os') > 0 ? 'https://itunes.apple.com/us/app/javashop/id1182297121': 'http://www.javamall.com/andorid.apk';

                var t = setTimeout(function() {
                    var endTime = Date.now();

                    if (!startTime || endTime - startTime < 3000) {
                        window.location = downurl;
                    }
                }, 1300);


                window.onblur = function() {
                    clearTimeout(t);
                }
            }
        }
    })

    // axios.get('/login?username=yumengshuai&password=111111')
    // .then(function (respone) {
    //     console.log(respone.data.data.content)
    // }).catch(function (error) {
    //     console.log(error)
    // });

