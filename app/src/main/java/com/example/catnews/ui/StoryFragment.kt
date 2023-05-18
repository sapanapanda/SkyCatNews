package com.example.catnews.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catnews.CatNewsApplication
import com.example.catnews.arch.ViewModelFactory
import com.example.catnews.databinding.FragmentStoryBinding
import com.example.catnews.network.response.ContentItem
import com.example.catnews.network.storydetail.StoryViewState
import com.example.catnews.viewModel.StoryViewModel
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val STORY_ID = "story_id"

/**
 * A simple [Fragment] subclass.
 * Use the [StoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoryFragment : Fragment() {
    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StoryViewModel
    private lateinit var storyDetailAdapter: StoryDetailAdapter
    private lateinit var id: String

    @Inject
    lateinit var factory: ViewModelFactory

    private val args: StoryFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory)[StoryViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CatNewsApplication)
            .applicationComponent
            .inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the RecyclerView adapter and set it to the RecyclerView
        storyDetailAdapter = StoryDetailAdapter()
        binding.rvContent.adapter = storyDetailAdapter
        binding.rvContent.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getStory(args.id)

        viewModel.responseLiveData.observe(viewLifecycleOwner) { state ->
            render(state)
        }
    }

    private fun render(state: StoryViewState) {
        when (state) {
            StoryViewState.Error -> {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "error occurred", Toast.LENGTH_SHORT).show()
            }

            StoryViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is StoryViewState.Success -> {
                binding.progressBar.visibility = View.GONE
                retrieveList(state.response.contents)
                binding.tvStoryHeadline.text = state.response.headline
            }
        }
    }


    private fun retrieveList(catDetails: List<ContentItem>) {
        storyDetailAdapter.apply {
            updateData(catDetails)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}