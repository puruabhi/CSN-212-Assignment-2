#include <bits/stdc++.h>
using namespace std;
const int maxn=20002;
int t,n,a[maxn];
/*
    Finds the Longest increasing subsequence in array a
    starting from a[start] to a[start+n]
*/
int lis(int start)
{
	int b[maxn],ans=0;
	for (int i=start; i<=start+n; i++)
	{
		int f=lower_bound(b+1,b+ans+1,a[i])-b;
		ans=max(ans,f);
		b[f]=a[i];
	}
	return ans;
}
/*
    So solution will the maximum of the longest increasing
    subsequence for each elements as the starting point
*/
int main()
{
	cin>>t;
	while (t--)
	{
		cin>>n;
		for (int i=1; i<=n; i++)
		{
			cin>>a[i]; a[i+n]=a[i];
		}
		int ans=0;
		for (int i=1; i<=n; i++) ans=max(ans,lis(i));
		cout<<ans<<endl;
	}
	return 0;
}
