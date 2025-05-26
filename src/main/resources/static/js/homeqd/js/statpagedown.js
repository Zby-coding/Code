function _vsb_showNewsStaticList(uid, size, duannum, pageCount, rowCount)
{
    var count = 0;
    var showobj;
    var start = (pageCount- rowCount % pageCount) % pageCount;
    for(i = start ; i < start + pageCount && i < size; i++)
    {
        count++;

        showobj = document.getElementById("line_" + uid + "_" + i);
        if(showobj)
            showobj.style.display="";

        showobj = document.getElementById("lineImage_" + uid + "_" + i);
        if(showobj)
            showobj.style.display="";

        if(duannum != 0 && count%duannum==0 && i + 1 < size && i + 1 < start + pageCount)
        {
            showobj = document.getElementById("section_" + uid + "_" + i);
            if(showobj)
                showobj.style.display="";
        }
    }
}