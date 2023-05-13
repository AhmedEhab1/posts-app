package com.stc.ahmedehabtask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stc.domain.entity.PostsResponse
import com.stc.ahmedehabtask.adapters.PostsAdapter
import com.stc.ahmedehabtask.viewModels.PostsViewModel
import com.stc.ahmedehabtask.R
import com.stc.ahmedehabtask.databinding.ListFragmentBinding
import com.stc.ahmedehabtask.utilities.EndlessRecyclerViewScrollListener
import com.stc.ahmedehabtask.utilities.Loading
import com.stc.data.db.DataBaseHelper.getPostsFromDb
import com.stc.data.db.DataBaseHelper.savaPostsList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ListFragment : Fragment(), PostsAdapter.PostClickListener {
    private lateinit var binding: ListFragmentBinding
    private val viewModel: PostsViewModel by viewModels()
    private val loading = Loading()
    private var page: Int = 1
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener
    private val adapter = PostsAdapter(mutableListOf(), this)
    private lateinit var  linearLayoutManager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        getPosts()
        initRec()
        apiErrorHandler()
    }

    private fun getPosts() {
        loading.show(requireActivity().supportFragmentManager, "loading")
        viewModel.getPosts(page)
        lifecycleScope.launch {
            viewModel.posts.collect {
                if (it != null) {
                    loading.dismiss()
                    adapter.addItems(it)
                    onScrollListener()
                    savaPostsList(requireActivity(), it)
                }
            }
        }
    }

    private fun initRec() {
        linearLayoutManager = LinearLayoutManager(
            requireActivity(),
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )
        binding.postsRv.adapter = adapter
        binding.postsRv.layoutManager = linearLayoutManager
    }

    private fun onScrollListener(){
        endlessRecyclerViewScrollListener =
            object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    viewModel.getPosts(page)
                }
            }
        binding.postsRv.addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    override fun onPostItemClick(position: PostsResponse) {
        val bundle = Bundle()
        bundle.putString("original_title" , position.original_title)
        bundle.putString("poster_path" , position.poster_path)
        Navigation.findNavController(requireView()).navigate(
            R.id.action_listFragment_to_detailsFragment, bundle
        )
    }

    private fun apiErrorHandler() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                loading.dismiss()
                lifecycleScope.launch(Dispatchers.Main) {
                    adapter.addItems(getPostsFromDb(requireActivity()))
                }
            }
        }
    }
}