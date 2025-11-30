fun main(args: Array<String>) {
   readLine()!!.split(' ').mapIndexed{i,num ->
    "${(i+97).toChar()} = $num"
    }.forEach{
        println(it)
    }

}