#include <stdio.h>

typedef struct MinHeap {
	int array[100];
	int last;
}min_heap_t;

min_heap_t minHeap = {{0}, -1};

void print_heap();

int peek()
{
	return minHeap.array[0];
}


void swap(int i, int j)
{
	int temp;

	temp = minHeap.array[i];
	minHeap.array[i] = minHeap.array[j];
	minHeap.array[j] = temp;
}


void sift_up()
{
	int i = minHeap.last;
	int n = -1;
	int temp;


	while (i != 0) {
		if (i % 2)
			n = (i - 1) / 2;
		else
			n = (i - 2) / 2;

		if (minHeap.array[i] < minHeap.array[n]) {
			swap(i, n);
			i = n;
		} else {
			break;
		}
	}
}


void insert(int item)
{
	minHeap.array[++minHeap.last] = item;
	if (minHeap.last == 0)
		return;
	sift_up();
}


void sift_down()
{
	int temp = 0;
	int i = 0;
	int n = 0;
	int stop = 0;


	if (minHeap.last % 2)
		stop = (minHeap.last - 1) / 2;
	else
		stop = (minHeap.last - 2) / 2;


	while (i <= stop) {
		n = (2 * i) + 1;

		if (minHeap.array[i] > minHeap.array[n]) {
			if (minHeap.array[n] <= minHeap.array[n+1]) {
				swap(i, n);
				i = n;
			} else {
				swap(i, n+1);
				i = n + 1;
			}
		} else if (minHeap.array[i] > minHeap.array[n+1]){
			swap(i, n+1);
			i = n + 1;
		} else {
			break;
		}
	}
}


void hremove()
{
	if (minHeap.last == -1)
		return;


	if (minHeap.last == 0) {
		minHeap.last--;
		return;
	}


	minHeap.array[0] = minHeap.array[minHeap.last--];
	print_heap();
	sift_down();
}


void print_heap()
{
	for (int i = 0; i <= minHeap.last; i++) {
		printf("%d ", minHeap.array[i]);
	}
	printf("\n");
}


int main(int argc, char **argv) {
	insert(10);
	insert(20);
	insert(3);
	insert(5);
	insert(13);
	insert(-2);
	insert(0);
	insert(6);
	insert(-10);
	insert(15);
	insert(22);

	print_heap();

	insert(12);
	print_heap();
	insert(-100);
	print_heap();
	hremove();
	print_heap();

	return 0;
}
