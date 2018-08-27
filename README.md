# MVVM-Dagger2-Retrofit2-LiveData

This is sample app to learn MVVM, Retrofit2, Dagger2. Here is the documention for the same.

## Recyclerview
Recyclerview has two main components:

- ViewHolder
- Adapter


The views in the list are represented by view holder objects. These objects are instances of a class you define by extending `RecyclerView.ViewHolder`. Each view holder is in charge of displaying a single item with a view.

> As the user scrolls through the list, the RecyclerView takes the off-screen views and rebinds them to the data which is scrolling onto the screen.

The view holder objects are managed by an adapter, which you create by extending RecyclerView.Adapter. The adapter creates view holders as needed. The adapter also binds the view holders to their data. It does this by assigning the view holder to a position, and calling the adapter's onBindViewHolder() method. That method uses the view holder's position to determine what the contents should be, based on its list position.

### Implementation
Adapter:
``` java
public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsViewHolder>{

    private List<UserDetails> userDetailsList;
    private ItemClickInteractionListener itemClickInteractionListener;

    public UserDetailsAdapter(List<UserDetails> userDetails, ItemClickInteractionListener itemClickInteractionListener) {
        this.userDetailsList = userDetails;
        this.itemClickInteractionListener = itemClickInteractionListener;
    }

    // Inflate View and return ViewHolder
    @NonNull
    @Override
    public UserDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserDetailsViewHolder(view);
    }

    // Get Item to add to list using position
    // Add Item to list using viewholder
    // viewHolder.view.findViewById -> Click or any Action handle here
    @Override
    public void onBindViewHolder(@NonNull UserDetailsViewHolder userDetailsViewHolder, int i) {
        UserDetails userDetails = this.userDetailsList.get(i);
        userDetailsViewHolder.bindItems(userDetails);

        // Handeling item clicks here
        // Can be implemented both here and in bindItems
        userDetailsViewHolder.itemView.findViewById(R.id.user_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickInteractionListener.onUserDetailsClick(String.valueOf(i));
            }
        });
    }

    // return the size
    @Override
    public int getItemCount() {
        return this.userDetailsList.size();
    }

    // Update the data whenever
    public void updateData(List<UserDetails> userDetails) {
        this.userDetailsList = userDetails;
        notifyDataSetChanged();
    }
}
```

ViewHolder:
``` java
public class UserDetailsViewHolder extends RecyclerView.ViewHolder {

    private View view;

    UserDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    // this can also be done in the adapter itself
    public void bindItems(UserDetails userDetails) {
        TextView name = view.findViewById(R.id.name);
        TextView email = view.findViewById(R.id.email);
        TextView phone = view.findViewById(R.id.phone);
        name.setText(userDetails.getName());
        email.setText(userDetails.getEmail());
        phone.setText(userDetails.getPhone());
    }
}
```
Now set this adapter to recyclerView, Also add a layoutmanager:
``` java
recyclerView = view.findViewById(R.id.user_list_view);
recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

adapter = new UserDetailsAdapter(new ArrayList<>(), itemClickInteractionListener);
recyclerView.setAdapter(adapter);
```

### Handle Clicks
To Handle clicks on the recyclerview items, create an Interface:
``` java
public interface ItemClickInteractionListener {
    void onUserDetailsClick(String id);
}
```

Implement this interface in Activity:
``` java
public class MainActivity extends AppCompatActivity implements ItemClickInteractionListener{
    @Override
    public void onUserDetailsClick(String id) {
        Toast.makeText(getApplicationContext(), "Clicked : " + id, Toast.LENGTH_SHORT).show();
    }
}
```

Attach context to `itemClickInteractionListener` in Fragment during `onAttach()`

``` java
public class HomeFragment extends Fragment {
    ItemClickInteractionListener itemClickInteractionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ItemClickInteractionListener) {
            itemClickInteractionListener = (ItemClickInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }
}
```

While creating Adapter Instance pass `itemClickInteractionListener` to the adapter.
``` java
adapter = new UserDetailsAdapter(new ArrayList<>(), itemClickInteractionListener);
```
## Retrofit 2

Create service interface:
``` java
interface ApiService  {

    @GET("api/")
    Call<UserList> userlist(@Query("results") String results);

}
```

Create Repository:

``` java
public class UserRepository {

    private ApiService apiService;
    private static UserRepository userRepository;

    private UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class);
    }

    public static UserRepository getInstance() {
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return new UserRepository();
    }

    public LiveData<UserList> getUserDetailList(int noOfUsers) {
        final MutableLiveData<UserList> liveData = new MutableLiveData<>();

        apiService.userlist(String.valueOf(noOfUsers)).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });

        return liveData;
    }

}
```

[Making Retrofit Work For You by Jake Wharton.](https://www.youtube.com/watch?v=t34AQlblSeE)

## ViewModel
Create view model: 
``` java
public class MyViewModel extends ViewModel {

    private final LiveData<UserList> userListLiveDataObservable;

    MyViewModel() {
        userListLiveDataObservable = UserRepository.getInstance().getUserDetailList(10);
    }


    public LiveData<UserList> getUserDetailsLiveDataObservable() {
        return userListLiveDataObservable;
    }
}
```
Data to viewmodel can be passed using Factory or set method.
Using viewmodel in Activity or Fragment: 
``` java

final MyViewModel viewModel =
        ViewModelProviders.of(this).get(MyViewModel.class);

observeViewModel(viewModel);
```
Observe LiveData:
``` java
private void observeViewModel(MyViewModel viewModel) {
    viewModel.getUserDetailsLiveDataObservable().observe(this, userList -> {
        List<UserDetails> userDetails = userList.getList();
        if(userDetails.size() > 0) {
            adapter.updateData(userDetails);
        }
    });
}
```
