/**
 * Created by Administrator on 2017/7/2.
 */
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello This!'
    }
})
var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于 ' + new Date()
    }
})
var app5 = new Vue({
        el: '#app-5',
        data: {
            message: 'Hello Vue.jsd111s!',
            image111:'asdadsadas'
        },
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            },
            loadData:function(){
                axios.get('/login?username=yumengshuai&password=111111')
                    .then(function (respone) {
                        app5.message = respone.data
                    }).catch(function (error) {
                    console.log(error)
                        this.message = error
                });
            }
        }
    })
    axios.get('/login?username=yumengshuai&password=111111')
    .then(function (respone) {
        console.log(respone.data.data.content)
    }).catch(function (error) {
        console.log(error)
    });

