package com.palak.applemusicappdemo.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.palak.applemusicappdemo.MainActivity
import com.palak.applemusicappdemo.R
import com.palak.applemusicappdemo.databinding.FragmentHomeBinding
import com.palak.applemusicappdemo.models.SongEntry
import com.palak.applemusicappdemo.utils.Constants
import com.palak.applemusicappdemo.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * This fragment show list of songs. Uses binding to update the data to UI.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private var adapter: SongAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController = findNavController()

        binding.homeViewModel = homeViewModel

        homeViewModel.songLiveData?.observe(viewLifecycleOwner) { songs ->

            if (songs != null && !songs.isNullOrEmpty()) {

                binding.rvSongs.layoutManager = LinearLayoutManager(context)
                adapter = SongAdapter { _, pos ->

                    val songEntryFromAdapter: SongEntry? = adapter?.currentList?.get(pos) as SongEntry?

                    songEntryFromAdapter?.let {

                        if(!Utils.isOnline(requireContext())) {
                            showInternetToast()
                            return@SongAdapter
                        }

                        val bundle = bundleOf(Constants.BUNDLE_SONG_ENTRY to it)
                        navController.navigate(
                            R.id.action_homeFragment_to_songDetailFragment,
                            bundle
                        )
                    }
                }

                binding.rvSongs.also {
                    it.adapter = adapter
                    it.isNestedScrollingEnabled = false
                    it.addItemDecoration(
                        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    )
                }

                adapter?.submitList(songs)
                binding.hasData = !songs.isNullOrEmpty()
            } else {
                binding.hasData = false
            }
        }

        homeViewModel.postingLiveData.observe(viewLifecycleOwner){
            println("received 1 on screen : $it")
        }

        homeViewModel.getPosts()

        if(Utils.isOnline(requireContext())){
            homeViewModel.fetchTopSongs()
        }
        else{
            showInternetToast()
        }

        setClickListener()
    }

    private fun setClickListener() {
        binding.btnPosts.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_postHomeFragment)
        }
    }

    private fun showInternetToast(){
        Toast.makeText(
            requireActivity().applicationContext,
            R.string.check_your_internet,
            Toast.LENGTH_SHORT
        ).show()
    }
}