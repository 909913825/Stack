import java.util.Arrays;

public class Array<E> {
    /**
     * 这里没有声明capacity，是因为data.length即为他可以存放的容量
     * 声明为private是因为不希望用户从外部直接获得这些信息，否则就是不安全的
     */
    private E[] data; //数组的名称
    private int size;   //数组中的有效元素

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }
    //无参的构造函数，默认的数组容量为10
    public Array(){
        this(10);
    }
    //获取数组中的元素个数
    public int getSize(){
        return size;
    }
    //获取数组中的容量
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void addLast(E e){
//        add(size,e);
        if (size == data.length){
            //抛出传入的参数错误的异常
            throw new IllegalArgumentException("AddLast failed, Array is already full.");
        }
        data[size] = e;
        size ++;
    }
    public void addFirst(E e){
        add(0,e);
    }
    public void add(int index, E e){
        if (size == data.length){
            //抛出传入的参数错误的异常
            throw new IllegalArgumentException("AddLast failed, Array is already full.");
        }
        if (index < 0 || index > size){
            throw new IllegalArgumentException("AddLast failed, Require index >= 0 and index <= size.");
        }
        for (int i = size - 1; i >= index; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
//        错误的做法
//        for (int j = index; j < size; j++){
//          data[j + 1]  = data[j];
//        }
//        data[index] = e;
//        size ++;
    }
    public void addPlus (int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("AddLast failed, Require index >= 0 and index <= size.");
        }
        if (size == data.length){
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
    private void resize(int newCapacity){
        E[] newData =(E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++){
           newData[i] = data[i];
        }
        data = newData;
    }



    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("AddLast failed, Index is illegal.");
        }
        return data[index];
    }
    public E getLast(){
        return get(size - 1);
    }
    public E getFirst(){
        return get(0);
    }
    void set(int index, E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("AddLast failed, Index is illegal.");
        }
        data[index] = e;
    }
    public boolean contains(E e){
        for (int i = 0 ;i < size; i ++){
            if (data[i].equals(e))
                return true;
        }
        return false;
    }
    public int find(E e){
        for (int i = 0 ;i < size; i ++){
            if (data[i].equals(e) )
                return i;
        }
        return -1;
    }
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("AddLast failed, Index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size ;i++){
            data[i - 1] = data[i];
        }
        size --;
        return ret;
    }
    public E removePlus(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("AddLast failed, Index is illegal.");
        }
        E ret = data[index];
//        for (int i = index; i < size; i ++){
//            data[i] = data[i + 1];
//        }
        for (int i = index + 1; i < size ;i++){
            data[i - 1] = data[i];
        }

        size --;
        data[size] = null;
        if(size <= data.length / 2){
            resize(data.length / 2);
        }

        return ret;
    }
    public  E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size - 1);
    }
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n",size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if (i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
