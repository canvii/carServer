<template>
	

	
	<view class="content">
			{{realPosX}} : {{realPosY}}
		<view class="text-area" >
			
		
				<image class="outerImg" src="../../static/out.png" v-bind:style="{height:outheight+'px',width:outWidth+'px'}"></image>
			
			
				<image   @touchend="touchend" @touchcancel="zero"   @touchmove="joystick($event)" class="innerImg"  src="../../static/in.png" v-bind:style="{'margin-left':toleft+'px','margin-top':totop+'px',height:innerheight+'px',width:innerWidth+'px'}" ></image>
		
		
			
		</view>
	</view>
</template> 

<script>
	export default {
		
		data() {
			
			return {
				title: 'Hello',
				centerX:100,
				centerY:100,
				toleft:'',
				totop:'',				
				innerWidth:100,
				innerheight:100,
				outWidth:100,
				outheight:100,
				radius:50,
				realPosX:'',
				realPosY:''
				
			}
		},
		onLoad() {

		},
		methods: {
			
			joystick(e){
				

				let touchX=e.touches[0].clientX
				let touchY=e.touches[0].clientY
				// console.log(e.touches[0].clientX+":"+e.touches[0].clientY);
				let movePosition =this.GetPosition(touchX,touchY,this.$data.centerX,this.$data.centerY)
				this.realPosX=movePosition.posX
				this.realPosY=movePosition.posY
				this.toleft=movePosition.posX
				this.totop=movePosition.posY
				
				uni.request({
					url:"http://192.168.12.1:8080/puppy/"+this.realPosX+"/"+this.realPosY
				})
			},
			zero(){
				this.$data.toleft= this.centerX-100;
				this.$data.totop= this.centerY-100;
				this.realPosX=0
				this.realPosY=0
				uni.request({
					url:"http://192.168.12.1:8080/puppy/"+0+"/"+0
				})
				
			},
		
			
			touchend(){
				this.$data.toleft= this.centerX-100;
				this.$data.totop= this.centerY-100;
				this.realPosX=0
				this.realPosY=0
				uni.request({
					url:"http://192.168.12.1:8080/puppy/"+0+"/"+0
				})
			},
			GetPosition (touchX, touchY,centerX,centerY) {
				
				var DValue_X;
				var Dvalue_Y;
				var Dvalue_Z;
				var imageX;
				var imageY;
				var ratio;
				
				DValue_X = touchX - this.$data.centerX;
				Dvalue_Y = touchY - this.$data.centerY;
				Dvalue_Z = Math.sqrt(DValue_X * DValue_X + Dvalue_Y * Dvalue_Y);
				//触碰点在范围内
				if (Dvalue_Z <= this.$data.radius) {
				imageX = touchX-100;
				imageY = touchY-100;
				imageX = Math.round(imageX);
				imageY = Math.round(imageY);
				return { posX: imageX, posY: imageY };
				}
				 
				//触碰点在范围外
				else {
				ratio = this.$data.radius / Dvalue_Z;
				imageX = DValue_X * ratio;
				imageY = Dvalue_Y * ratio ;
				
				imageX = Math.round(imageX);
				imageY = Math.round(imageY);
				return { posX: imageX, posY: imageY };
				}
			}
		}
		
	}
	
</script>

<style>

	.outerImg {
		background-color: #C0C0C0;
		padding-left: 50px;
		padding-top: 50px;
		position:absolute;
		mode:"aspectFill";
		
	}
	.innerImg{
		padding-left: 50px;
		padding-top: 50px;
		position:absolute;
		mode:"aspectFill";
		
		
	}
	
	.content {
		/* display: flex; */
		/* align-items: flex-start; */
		/* justify-content: left; */
		margin-left: 0px;
		margin-top: 0px;
		position: absolute;
	}

	

	.text-area {
		/* display: flex; */
		
		position: relative;

		
		
	}

	.title {
		font-size: 36px;
		color: #8f8f94;
	}
	*{
	padding:0;            /*清除内边距*/
	
	margin:0;              /*清除外边距*/
	
	}
</style>
