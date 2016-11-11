#include <stdio.h>

#define MAX_SIZE 100
int a[MAX_SIZE] = {0, 1, 4, -1, 3, 33, 60, 10};

int b_search(int item, int left, int right)
{
	int mid = 0;
	int ret = -1;
	printf("%s\n", __func__);
	
	if (left > right)
		return -1;

	if (left == right) {
		if (a[left] == item)
			return left;
		else
			return -1;
	}

	if (item == a[left])
		return left;

	if (item == a[right])
		return right;

	mid = (left + right) / 2;

	if (a[mid] == item)
		return mid;

	ret = b_search(item, left, mid);
	if (ret != -1)
		return ret;

	ret = b_search(item, mid+1, right);

	return ret;
}


int main(int argc, char **argv)
{
	int idx = -1;
	idx = b_search(10, 0, 7);
	printf("%d\n", idx);
	return 0;
}
