package com.asterix.datastructure;


public class MinHeap {
 
    /** Fixed-size array heap representation */
    private int[ ] array;
    /** Number of nodes in the heap (array) */
    private int n = 0;
 
    /** Constructs a heap of specified size */
    public MinHeap( final int size ) {
        array = new int[ size ];
    }
 
    /** Returns (without removing) the smallest (min) element from the heap. */
    public int min( ) {
        if( isEmpty( ) ) {
            throw new RuntimeException( "Heap is empty!" );
        }
 
        return array[ 0 ];
    }
 
    /** Removes and returns the smallest (min) element from the heap. */
    public int removeMin( ) {
        if( isEmpty( ) ) {
            throw new RuntimeException( "Heap is empty!" );
        }
 
        final int min = array[ 0 ];
        array[ 0 ] = array[ n - 1 ];
        if( --n > 0 ) siftDown( 0 );
        return min;
    }
 
    /** Checks if the heap is empty. */
    public boolean isEmpty( ) {
        return n == 0;
    }
 
    /** Adds a new element to the heap and sifts up/down accordingly. */
    public void add( final int value ) {
        if( n == array.length ) {
            throw new RuntimeException( "Heap is full!" );
        }
 
        array[ n ] = value;
        siftUp( n );
        n++;
    }
 
    /**
     * Sift up to make sure the heap property is not broken.
     * This method is used when a new element is added to the heap 
     * and we need to make sure that it is at the right spot.
     */
    private void siftUp( final int index ) {
        if( index > 0 ) {
            final int parent = ( index - 1 ) / 2;
            if( array[ parent ] > array[ index ] ) {
                swap( parent, index );
                siftUp( parent );
            }
        }
    }
 
    /** 
     * Sift down to make sure that the heap property is not broken 
     * This method is used when removing the min element, and we need 
     * to make sure that the replacing element is at the right spot.
     */
    private void siftDown( int index ) {
    	
        final int leftChild = 2 * index + 1;
        final int rightChild = 2 * index + 2;
 
        // Check if the children are outside the array bounds.
        if( rightChild >= n && leftChild >= n ) return;
 
        // Determine the smallest child out of the left and right children.
        final int smallestChild = 
            array[ rightChild ] > array[ leftChild ] ? leftChild : rightChild;
 
        if( array[ index ] > array[ smallestChild ] ) {
            swap( smallestChild, index );
            siftDown( smallestChild );
        }
    }
 
    /** Helper method for swapping array elements */
    private void swap( int a, int b ) {
        int temp = array[ a ];
        array[ a ] = array[ b ];
        array[ b ] = temp;
    }
    
    
 
    /** Test Method */
    public static void main( String[ ] args ) {
 
    	
        int[ ] input = { 6, 5, 3, 1, 8, 7, 2, 4, 6, -1, -4 };
        MinHeap heap = new MinHeap( input.length );
        for( int i = 0; i < input.length; i++ ) {
            heap.add( input[ i ] );
        }
 
        while( ! heap.isEmpty( ) ) {
            System.out.print( heap.removeMin( ) + " " );
        }
    }
}
