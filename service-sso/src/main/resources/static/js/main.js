var FORMAT_DATETIME = 'YYYY-MM-DD HH:mm:ss Z';

$(function() {
	$('.d-ldt').each(function() {
        if ($(this).text() && $(this).text().trim() !== '') {
            $(this).text(moment.utc($(this).text().trim()).local().format(FORMAT_DATETIME));
        }
    });
});
