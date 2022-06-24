package com.example.geekbrainstranslator.view.description

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentDescriptionWordBinding
import com.example.geekbrainstranslator.view.description.viewmodel.DescriptionWordViewModel
import com.example.geekbrainstranslator.view.main.MainTranslationFragment
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class DescriptionWordFragment : Fragment(), DescriptionWordContract.View {

    private var _binding: FragmentDescriptionWordBinding? = null
    private val binding get() = _binding!!

    private var dataItem = ""

    private val scope = getKoin().getOrCreateScope<DescriptionWordFragment>(SCOPE_ID)

    private val adapter: DescriptionWordRvAdapter by lazy {
        scope.get(named("description_rv_adapter"))
    }

    private val viewModel: DescriptionWordViewModel by lazy {
        scope.get(named("description_view_model"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionWordBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        const val SCOPE_ID = "descriptionScope"
        fun newInstance(itemName: String): DescriptionWordFragment {
            val fragment = DescriptionWordFragment()
            fragment.dataItem = itemName
            return fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.descriptionActionBar.inflateMenu(R.menu.details_menu)
        dataItem.let {
            viewModel.setWordDetails(it)
        }
        initRv()
        setData()
        toolbarMenuClicker()
    }

    override fun onDestroy() {
        scope.close()
        _binding = null
        super.onDestroy()
    }

    override fun setData() {
        viewModel.wordDetails.observe(viewLifecycleOwner) {
            binding.entityText.text = it.text
            adapter.setData(it)
        }
    }

    private fun initRv() {
        binding.rvWordDetails.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvWordDetails.adapter = adapter
    }

    private fun toolbarMenuClicker() {
        binding.descriptionActionBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.description_history_fragment -> {
                    Toast.makeText(requireContext(), "FAVORITE", Toast.LENGTH_SHORT).show()
                }
                R.id.description_home_fragment -> {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_activity_container,
                            MainTranslationFragment.newInstance()
                        )
                        .commit()
                }
            }
            true
        }
    }
}