var app = new Vue({
    el: '#app',
    data: {
        books: [],
        display: {},
        this_book: {id: 0, name: "", category: {name: ""}}
    },
    methods: {
        returnBook: function () {
            my.returnBook($("#see-id").val());
        },

        checkBox: function (bookId) {
            $("#book-table").hide();
            $("#see-form").show();
            $.getJSON({
                url: "/api/books/" + bookId,
                success: function (data) {
                    data.body.canRead = true;
                    data.body.canReturn = false;
                    data.body.canBorrow = true;
                    if (data.code === 0) {
                        if (data.body.borrower !== null) {
                            data.body.canBorrow = false;
                            if (data.body.borrower.id === nav.user.id)
                                data.body.canReturn = true;
                        }
                        my.readBooks.forEach(function (book) {
                            if (book.id === data.body.id)
                                data.body.canRead = false;
                        });
                        app.this_book = data.body
                    } else {
                        alert("获取失败")
                    }
                },
                error: function (data) {
                    console.log(url + " ajax失败")
                }
            })
        },

        borrowBook: function () {
            $.ajax({
                url: "/api/books/" + $("#see-id").val() + "/borrow",
                dataType: "json",
                type: "POST",
                success: function (data) {
                    if (data.code === 0) {
                        location.reload();
                    }
                    else {
                        alert("更新失败")
                    }
                },
                error: function (data) {
                    console.log(url + " ajax失败")
                }
            })
        },

        readBook: function () {
            $.ajax({
                url: "/api/books/" + $("#see-id").val() + "/read",
                dataType: "json",
                type: "POST",
                success: function (data) {
                    if (data.code === 0) {
                        location.reload();
                    }
                    else {
                        alert("更新失败")
                    }
                },
                error: function (data) {
                    console.log(url + " ajax失败")
                }
            })
        },

        backMain: function () {
            $("#book-table").show();
            $("#see-form").hide()
        }

    }
});

var nav = new Vue({
    el: "#nav",
    data: {user: {}}
});

var my = new Vue({
    el: "#my",
    data: {
        borrowBooks: [],
        readBooks: [],
        this_book: {id: 0, name: "", category: {name: ""}}
    },
    methods: {
        returnBook: function (bookId) {
            $.ajax({
                url: "/api/books/" + bookId + "/return",
                dataType: "json",
                type: "POST",
                success: function (data) {
                    if (data.code === 0) {
                        location.reload();
                    }
                    else {
                        alert("还书失败")
                    }
                },
                error: function (data) {
                    console.log(url + " ajax失败")
                }
            })
        }
    }
});


function getBooks() {
    $.getJSON({
        url: "/api/books/", dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                data.body.forEach(function (book) {
                    if (book.borrower === null) book.borrower = "可借";
                    else book.borrower = "已借出";
                });
                app.books = data.body
            } else {
                console.log(data.message)
            }
        },
        error: function (data) {
            console.log(url + " ajax失败");
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
                    if (book.borrower === null) book.borrower = "可借";
                    else book.borrower = "已借出";
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

function getBorrowBooks() {
    $.getJSON({
        url: "/api/books/my?borrow=true", dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                my.borrowBooks = data.body
            } else {
                console.log(data.message)
            }
        },
        error: function (data) {
            console.log(url + " ajax失败");
        }
    });
}
function getReadBooks() {
    $.getJSON({
        url: "/api/books/my?read=true", dataType: "json",
        success: function (data) {
            if (data.code === 0) {
                my.readBooks = data.body
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
                nav.user = data.body
            } else {
                console.log(data.message);
                location.href = "/login"
            }
        },
        error: function (data) {
            console.log(url + " ajax失败");
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

    getBorrowBooks();
    getReadBooks();
});


