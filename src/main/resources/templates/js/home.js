/**
 * Created by Administrator on 2017/7/2.
 */
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
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
            message: 'Hello Vue.js!'
        },
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            }
        }
    })
    axios.get('http://127.0.0.1:8080/login?username=yumengshuai&password=111111')
    .then(function (respone) {
        console.log(respone.data.data.content);
    }).catch(function (error) {
        console.log(error);
    });

