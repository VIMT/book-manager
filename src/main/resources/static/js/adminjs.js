var app = new Vue({
    el: '#app',
    data: {
        books: [],
        display: {},
        this_book: {id:0, name:"", category:{name:""}}
    },
    methods: {

        showAdd: function () {
            $("#book-table").hide();
            $("#see-form").hide()
            $("#add-form").show()
        },

        addBook: function () {
            $.post({
                url: "/books/add",
                dataType:"json",
                data: $("#add-form").serialize(),
                success: function (data) {
                    if(data.code === 0){
                        location.reload();
                    }
                    else{
                        alert("添加失败")
                    }
                },
                error: function (data) {
                    console.log("ajax异常")
                }
            })
        },

        checkBox: function (e) {
            $("#book-table").hide();
            $("#add-form").hide()
            $("#see-form").show()
            this_ele = e.currentTarget;
            $.getJSON({
                url: "/books/" + this_ele.parentElement.parentElement.firstElementChild.innerText,
                success: function (data) {
                    if (data.code === 0) {
                        app.this_book = data.body
                    } else {
                        alert("获取失败")
                    }
                },
                error: function (data) {
                    console.log("ajax异常")
                }
            })
        },

        updateBook: function () {
            $.ajax({
                url: "/books/",
                dataType:"json",
                data: $("#see-form").serialize(),
                type: "PUT",
                success: function (data) {
                    if(data.code === 0){
                        location.reload();
                    }
                    else{
                        alert("更新失败")
                    }
                },
                error: function (data) {
                    console.log("ajax异常")
                }
            })
        },

        deleteClick: function (e) {
            this_ele = e.currentTarget;
            $.ajax({
                url: "/books/" + this_ele.parentElement.parentElement.firstElementChild.innerText,
                type: "DELETE",
                success: function (data) {
                    if (data.code === 0) {
                        getBooks()
                    } else {
                        alert("删除失败")
                    }
                },
                error: function (data) {
                    console.log("ajax异常")
                }
            })
        },

        backMain: function () {
            $("#book-table").show();
            $("#add-form").hide()
            $("#see-form").hide()
        }

    }
});

var nav = new Vue({
    el: "#nav",
    data: {user: {}}
});
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

function getBooks() {
    page = getUrlParam("page");
    if(page===null) page=0;
    $.getJSON({
        url: "/books/" + "?page=" + page, dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                app.books = data.body
            } else {
                console.log(data.message)
            }
        },
        error: function (data) {
            console.log("读取jsonName error!");
        }
    });
}

$(function () {
    $.getJSON({
        url: "/user/info", dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                nav.user = data.body
            } else {
                console.log(data.message);
                location.href = "/login"
            }
        },
        error: function (data) {
            console.log("ajax异常");
        }
    });

    getBooks();
});


