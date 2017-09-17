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
                window.location='Javashop://m.javashop.com/main/image'
                var ua = navigator.userAgent.toLowerCase();
                var startTime = Date.now();
                var downurl = ua.indexOf('os') > 0 ? 'https://itunes.apple.com/us/app/javashop/id1040031725': 'http://www.taobao.com';

                var t = setTimeout(function() {
                    var endTime = Date.now();

                    if (!startTime || endTime - startTime < 1300 + 200) {
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

