r=0,g=0,b=0;

function init(){
    pI=new Array(256);
    l=new Array(16);
    l[10]='a',l[11]='b',l[12]='c',l[13]='d',l[14]='e',l[15]='f';
    for(i=0;i<10;i++)
        l[i]=''+i;
    for(i=0,I=0;i<16;i++)
        for(j=0;j<16;j++,I++)
            pI[I]=l[i]+l[j];
    f(0,0,0);   
}

function f(r,g,b){
    if(r==g&&g==b)
        r+=10;
    else if(r>g&&g>=b)
        g+=10;
    else if(r<=g&&g>b&&r>0)
        r-=10;
    else if(g>b&&g>r)
        b+=10;
    else if(b>=g&&b>r&&g>0)
        g-=10;
    else if(r<=b&&g<b)
        r+=20;
    else if(r>g&&g<=b&&b>0)
        b-=10;
    if(r>255||g>255||b>255)
        r=g=b=0;
document.bgColor="#"+pI[r]+pI[g]+pI[b];
setTimeout("f("+r+","+g+","+b+")",500);
}
function ocio(){
    window.alert("Per salvare l'immagine:\nTasto destro -> 'copia immagine'");
    window.document.forms.ff.submit()
}