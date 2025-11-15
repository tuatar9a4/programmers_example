class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {

        var giftScoreList = calculateGiftScore(friends,gifts)
        var giftTradeInfo = calculateTradeCount(friends,gifts)
        var nextMonthReceive = Array(friends.size,{0})
        
        for(i in 0 until giftTradeInfo.size-1){
            for(j in i+1 until giftTradeInfo[i].size){
                if(giftTradeInfo[i][j] > giftTradeInfo[j][i]) nextMonthReceive[i]++
                else if(giftTradeInfo[i][j] == giftTradeInfo[j][i])
                {
                    if(giftScoreList[i] == giftScoreList[j]) continue
                    else if(giftScoreList[i] > giftScoreList[j]) nextMonthReceive[i]++
                    else nextMonthReceive[j]++
                }
                else nextMonthReceive[j]++
            }
        }

        return nextMonthReceive.maxOrNull()?:0
    }
    
    private fun calculateGiftScore(friends : Array<String>,giftsInfo : Array<String>) : Array<Int>{
        var giftScoreList = Array(friends.size,{0})
        giftsInfo.forEach{
            val giftsInfo = it.split(" ")
            val sendFriend = giftsInfo[0]
            val receiveFriend = giftsInfo[1]
            if(friends.indexOf(sendFriend) != -1) giftScoreList[friends.indexOf(sendFriend)]++
            if(friends.indexOf(receiveFriend) != -1)giftScoreList[friends.indexOf(receiveFriend)]--
        }
        return giftScoreList
    }
    
    private fun calculateTradeCount(friends : Array<String>,giftsInfo : Array<String>) : Array<IntArray>{
        var giftTradeInfo = Array<IntArray>(friends.size,{IntArray(friends.size,{0})})
        giftsInfo.forEach{
            val giftsInfo = it.split(" ")
            val sendFriend = giftsInfo[0]
            val receiveFriend = giftsInfo[1]
            if(friends.indexOf(sendFriend) != -1 && friends.indexOf(receiveFriend) != -1){
                giftTradeInfo[friends.indexOf(sendFriend)][friends.indexOf(receiveFriend)]++
            }
        }
       return giftTradeInfo
    }
}