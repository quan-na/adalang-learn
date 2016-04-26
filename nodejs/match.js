// use with argument
// node match.js 'string to match' 'pattern to match'

function domatch(str, patt) {
    var match = function (spos, ppos) {
        if (spos == str.length && ppos == patt.length)
            return true;
        if (ppos == patt.length)
            return false;
        switch (patt.charAt(ppos)) {
        case "?":
            return match(spos+1, ppos+1);
        case "*":
            return match(spos, ppos+1) || (spos <= str.length && match(spos+1, ppos));
        default:
            return (patt.charAt(ppos) == str.charAt(spos)) && match(spos+1, ppos+1);
        }
    };
    return match(0, 0);
}

if (domatch(process.argv[2], process.argv[3]))
    console.log("cool.");
else
    console.log("get out.");
