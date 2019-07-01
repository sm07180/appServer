<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    
    ${out_pt.y }
    
    
    
    
<script src="js/GeoTrans.js"></script>
<script type="text/javascript">
var geo = new GeoTrans();
geo.init("geo", "katec");

var pt = new Point(${x}, ${y});
var out_pt = geo.conv(pt);
/* alert("[KATEC 좌표]\nx : "+out_pt.x+"\ny : "+out_pt.y); */ 
document.write("!@#"+out_pt.x+"!@#");
document.write("!@#"+out_pt.y+"!@#");


 
//역변환
/* geo.init("katec", "geo");
out_pt = geo.conv(out_pt);

alert("경도 : "+out_pt.x+"\n위도 : "+out_pt.y); */
/* location.href = "katec2?x="+out_pt.x +"&y="+out_pt.y; */
</script>