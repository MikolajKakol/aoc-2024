package util

class BinaryNode<T>(var value: T) {

    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

}

fun <T> BinaryNode<T>.traverseLeafs(action: (BinaryNode<T>) -> Unit) {
    if (leftChild == null && rightChild == null){
        action(this)
    }else{
        leftChild?.traverseLeafs(action)
        rightChild?.traverseLeafs(action)
    }
}
