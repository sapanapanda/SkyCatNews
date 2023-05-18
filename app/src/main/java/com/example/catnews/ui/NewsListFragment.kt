package com.example.catnews.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catnews.CatNewsApplication
import com.example.catnews.arch.ViewModelFactory
import com.example.catnews.databinding.FragmentNewsListBinding
import com.example.catnews.network.response.CatNewsItem
import com.example.catnews.network.storylist.ViewState
import com.example.catnews.viewModel.NewsListViewModel
import javax.inject.Inject

class NewsListFragment : Fragment(), OnClickListener {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    @Inject
    lateinit var factory: ViewModelFactory


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CatNewsApplication)
            .applicationComponent
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[NewsListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsListAdapter = NewsListAdapter(this)
        binding.rvStoryList.adapter = newsListAdapter
        binding.rvStoryList.layoutManager = LinearLayoutManager(requireContext())

        val itemDecoration = DividerItemDecoration(requireContext())
        binding.rvStoryList.addItemDecoration(itemDecoration)
        viewModel.getNews()
        viewModel.responseLiveData.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun render(state: ViewState) {
        when (state) {
            ViewState.Error -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "error occurred", Toast.LENGTH_SHORT).show()
            }

            ViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is ViewState.Success -> {
                binding.progressBar.visibility = View.GONE
                displayList(state.response.data)
                binding.tvTitle.text = state.response.title
            }
        }
    }

    private fun displayList(catDetails: List<CatNewsItem>) {
        newsListAdapter.apply {
            updateData(catDetails)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(id: String) {
        val action =
            NewsListFragmentDirections.actionNewsListFragmentToStoryFragment(
                id
            )
        view?.findNavController()?.navigate(action)
    }
}