var app = new Vue({
    el: '#app',
    data: {
        books: [],
        display: {},
        this_book: {id:0, name:"", category:{name:""}},
        readers: []
    },
    methods: {

        showAdd: function () {
            $("#book-table").hide();
            $("#see-box").hide()
            $("#add-form").show()
        },

        addBook: function () {
            $.post({
                url: "/api/admin/books/",
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

        checkBox: function (bookId) {
            $("#book-table").hide();
            $("#add-form").hide();
            $("#see-box").show();
            $.getJSON({
                url: "/api/books/" + bookId,
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
            });
            $.getJSON({
                url: "/api/admin/books/" + bookId + "/readers",
                success: function (data) {
                    if(data.code === 0) {
                        app.readers = data.body
                    } else {
                        alert("获取读者失败")
                    }
                },
                error: function (data) {
                    console.log(data)
                }
            })
        },

        updateBook: function () {
            $.ajax({
                url: "/api/admin/books/",
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

        deleteClick: function (bookId) {
            $.ajax({
                url: "/api/admin/books/" + bookId,
                type: "DELETE",
                success: function (data) {
                    if (data.code === 0) {
                        location.reload()
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
            $("#see-box").hide()
        }

    }
});

var nav = new Vue({
    el: "#nav",
    data: {user: {}}
});

var users = new Vue({
    el: "#users",
    data: {
        users: []
    }
});


function getBooks() {
    $.getJSON({
        url: "/api/books/", dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                data.body.forEach(function (book) {
                    if (book.borrower === null) book.borrower = "";
                    else book.borrower = book.borrower.username;
                });
                app.books = data.body;
            } else {
                console.log(data.message)
            }
        },
        error: function (data) {
            console.log("读取jsonName error!");
        }
    });
}

var page = 1;
var scroll_stop = false;
var page_get = [];
function getMoreBooks() {
    $.getJSON({
        url: "/api/books/" + "?page=" + page, dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                data.body.forEach(function (book) {
                    if (book.borrower === null) book.borrower = "";
                    else book.borrower = book.borrower.username;
                });
                app.books = app.books.concat(data.body);
                if (data.body.length < 20) scroll_stop = true;
                else page++;
            } else {
                console.log(data.message)
            }
        },
        error: function (data) {
            console.log(url + " ajax失败");
        }
    });
}

function changeMain(name) {
    changeMain.current.hide();
    $("#" + name).show();
    changeMain.current = $("#" + name);
}

$(function () {
    $.getJSON({
        url: "/user/info", dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                if(data.body.admin === false) {
                    alert("你不是管理员");
                    location.href = "/";
                }
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
    changeMain.current = $("#app");
    changeMain("app");

    $("#app").scroll(function () {
        var $this = $(this),
            viewH = $this.height(),//可见高度
            contentH = $this.get(0).scrollHeight,//内容高度
            scrollTop = $this.scrollTop();//滚动高度
        //if(contentH - viewH - scrollTop <= 100) { //到达底部100px时,加载新内容
        if (!scroll_stop) {
            if (scrollTop / (contentH - viewH) >= 0.95) { //到达底部100px时,加载新内容
                if (page_get[page] === undefined) {
                    getMoreBooks();
                    page_get[page] = true;
                }
            }
        }
    });

    $.getJSON({
        url: "/api/admin/users/",
        success: function (data) {
            users.users = data.body
        }
    })
});


