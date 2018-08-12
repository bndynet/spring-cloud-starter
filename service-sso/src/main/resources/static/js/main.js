var FORMAT_DATETIME = 'YYYY-MM-DD HH:mm:ss Z';

$(function() {
    // convert UTC time to local time
    $('.d-ldt').each(function() {
        if ($(this).text() && $(this).text().trim() !== '') {
            $(this).text(moment.utc($(this).text().trim()).local().format(FORMAT_DATETIME));
        }
    });

    // remove events
    $('.action-remove').click(function(e){
        e.preventDefault();
        var sender = $(this);
        alertify.confirm(i18n.msgDeleteConfirm, function() {
            location.href = sender.attr('href');	               
        });
    });
});
