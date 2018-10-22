layui.use(['table'], function () {
    var table = layui.table;
    table.render({
        elem: '#test',
        url: '/sys/menu_list',
        page: {
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
            curr: 1,
            groups: 1,
            first: false,
            last: false
        },
        cols: [[
            {field: 'id', width: 80, title: 'ID', sort: true}
            , {field: 'url', width: 100, title: '路劲'}
            , {field: 'name', width: 80, title: '名称', sort: true}
        ]]
    });
});