var outfile = phantom.args[1];
var urls = [];
phantom.args.forEach(function(argmtns) {
	if (argmtns.indexOf("http") !== -1) {
		urls.push(argmtns);
	}
});

pjs.config({
    writer: 'file',
    outFile: outfile,
    maxDepth: 0,
});

pjs.addSuite({
    title: 'fabfurnish Scraper Suite',
    url: urls,
    scrapers: [ 
        function() {
        	return $('#productsCatalog>li')
        	.map(function() {
        		var name = $(this).find('a .itmTitleCont').text().trim(),
        		regularPrice = $(this).find('a .itm-price.special').text().trim(),
        		specialPrice = $(this).find('a .itm-price.old').text().trim(),
        		link = $(this).find('a.itm-link').attr('href'),
        		Image = $(this).find('a span.itm-imageWrapper').attr('id');
        		return {
		                    name: name,
		                    link: link,
		                    image : Image,
		                    regularPrice: regularPrice,
		                    specialPrice: specialPrice,
		                    site: "fabfurnish"
		       }
                     }).toArray();
        }
       ]
});