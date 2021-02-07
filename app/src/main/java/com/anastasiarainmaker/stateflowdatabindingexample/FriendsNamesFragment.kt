package com.anastasiarainmaker.stateflowdatabindingexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anastasiarainmaker.stateflowdatabindingexample.databinding.FragmentFriendsNamesBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FriendsNamesFragment : Fragment() {

    private var _binding: FragmentFriendsNamesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel =
        MainViewModel(MainRepository())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendsNamesBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.viewModel = viewModel
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}